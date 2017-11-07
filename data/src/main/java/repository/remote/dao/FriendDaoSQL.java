package repository.remote.dao;

import model.User;
import repository.remote.mapper.UserMapper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class FriendDaoSQL extends BaseDaoSQL {

    public Boolean addFriend(String token, String login) {
        String query = "" +
                "INSERT INTO \n" +
                "`database`.`friends` (`user_id`, `friend_id`) \n" +
                "VALUES \n" +
                "((SELECT users.id FROM users WHERE token=?), " +
                "(SELECT users.id FROM users WHERE login=?));";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, token);
            statement.setString(2, login);
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

    public ArrayList<User> getFriendList(String token) {
        String query = "" +
                "SELECT\n" +
                "`database`.f.login,\n" +
                "`database`.f.name\n" +
                "FROM \n" +
                "`database`.friends \n" +
                "LEFT JOIN \n" +
                "`database`.users as f ON friends.friend_id = f.id\n" +
                "WHERE \n" +
                "(user_id = (SELECT id FROM users WHERE token = ?))";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, token);

            return UserMapper.map(statement.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
