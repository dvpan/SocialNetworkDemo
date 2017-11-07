package repository.remote.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by danpa on 03.11.2017.
 */
public class UserDaoSQL extends BaseDaoSQL {

    public Boolean signUp(String name, String login, int hashPass) {
        String query = "INSERT INTO users (name, login, hash_password) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, name);
            statement.setString(2, login);
            statement.setInt(3, hashPass);
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

    public String signIn(String login, int hashPass, String token) {
        String query = "UPDATE `database`.`users` SET `token`= ? " +
                "WHERE (`login`= ? AND `hash_password` = ?);";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, token);
            statement.setString(2, login);
            statement.setInt(3, hashPass);
            int count = statement.executeUpdate();
            connection.commit();
            return count > 0 ? token : "ERROR";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
