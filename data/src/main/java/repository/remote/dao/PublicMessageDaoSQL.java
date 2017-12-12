package repository.remote.dao;

import model.PublicMessage;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import repository.remote.mapper.PublicMessageMapper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class PublicMessageDaoSQL extends BaseDaoSQL {

    @CacheEvict(value = "public_messages", allEntries = true)
    public Boolean sendPublicMessage(String token, String text, long time) {
        String query = "" +
                "INSERT INTO \n" +
                "`database`.`public_messages` (`user_id`, `text`, `date`) \n" +
                "VALUES \n" +
                "((SELECT users.id FROM users WHERE token=?), ?, ?);\n";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, token);
            statement.setString(2, text);
            statement.setLong(3, time);
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

    @Cacheable(value = "public_messages")
    public ArrayList<PublicMessage> getPublicMessageList(String login) {
        String query = "" +
                "SELECT * \n" +
                "FROM \n" +
                "`database`.public_messages \n" +
                "WHERE \n" +
                "(user_id = (SELECT id FROM users WHERE login = ?)) ";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, login);

            return PublicMessageMapper.map(statement.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
