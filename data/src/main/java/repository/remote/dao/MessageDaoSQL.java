package repository.remote.dao;

import model.Message;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import repository.remote.mapper.MessageMapper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danpa on 03.11.2017.
 */
public class MessageDaoSQL extends BaseDaoSQL {

    @CacheEvict(value = "messages", allEntries = true)
    public Boolean sendMessage(String token, String login, String text, long time) {
        String query = "" +
                "INSERT INTO \n" +
                "`database`.`messages` (`from_id`, `to_id`, `text`, `date`) \n" +
                "VALUES \n" +
                "((SELECT users.id FROM users WHERE token=?), (SELECT users.id FROM users WHERE login=?), ?, ?);\n";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, token);
            statement.setString(2, login);
            statement.setString(3, text);
            statement.setLong(4, time);
            statement.execute();
            connection.commit();
            return true;
        } catch (SQLException e2) {
            throw new RuntimeException(e2);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Cacheable(value = "messages")
    public ArrayList<Message> getMessageList(String token, String login) {
        String query = "" +
                "SELECT \n" +
                "`database`.messages.id,\n" +
                "`database`.f.name as from_name, \n" +
                "`database`.t.name as to_name, \n" +
                "`database`.messages.text,\n" +
                "`database`.messages.date\n" +
                "FROM \n" +
                "`database`.messages \n" +
                "LEFT JOIN \n" +
                "`database`.users as f ON messages.from_id = f.id\n" +
                "LEFT JOIN \n" +
                "`database`.users as t ON messages.to_id = t.id\n" +
                "\n" +
                "WHERE \n" +
                "(from_id = (SELECT id FROM users WHERE token = ?) AND to_id = (SELECT id FROM users WHERE login = ?)) \n" +
                "OR \n" +
                "(from_id = (SELECT id FROM users WHERE login = ?) AND to_id = (SELECT id FROM users WHERE token = ?));";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, token);
            statement.setString(2, login);
            statement.setString(3, login);
            statement.setString(4, token);

            return MessageMapper.map(statement.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Cacheable(value = "messages")
    public List<String> getMessagesText(String token) {
        String query = "" +
                "SELECT \n" +
                "`database`.messages.text\n" +
                "FROM \n" +
                "`database`.messages \n" +
                "WHERE \n" +
                "(from_id = (SELECT id FROM users WHERE token = ?))";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, token);

            ResultSet resultSet = statement.executeQuery();

            ArrayList<String> result = new ArrayList<>();
            while (resultSet.next())
                result.add(resultSet.getString("text"));

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
