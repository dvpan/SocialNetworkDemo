package repository.remote.mapper;

import model.PublicMessage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by danpa on 05.11.2017.
 */
public class PublicMessageMapper {
    public static ArrayList<PublicMessage> map(ResultSet set) {
        ArrayList<PublicMessage> messages = new ArrayList<>();

        try {
            while(set.next()){
                PublicMessage message = new PublicMessage(
                    set.getInt("user_id"),
                    set.getString("text"),
                    set.getLong("date")
                );
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }
}
