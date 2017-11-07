package repository.remote.mapper;

import model.Message;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by danpa on 05.11.2017.
 */
public class MessageMapper {
    public static ArrayList<Message> map(ResultSet resultSet) {
        ArrayList<Message> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Message msg = new Message(
                        resultSet.getString("text"),
                        resultSet.getString("from_name"),
                        resultSet.getString("to_name"),
                        resultSet.getLong("date")
                );
                result.add(msg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
