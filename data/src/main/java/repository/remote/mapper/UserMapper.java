package repository.remote.mapper;

import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by danpa on 06.11.2017.
 */
public class UserMapper {
    public static ArrayList<User> map(ResultSet set) {
        ArrayList<User> users = new ArrayList<>();

        try {
            while(set.next()){
                User user = new User(
                    set.getString("name"),
                    set.getString("login")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
