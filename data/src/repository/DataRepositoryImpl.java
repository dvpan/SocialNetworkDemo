package repository;

import db.DatabaseSingleton;
import model.Message;
import model.PublicMessage;
import model.User;
import model.Word;
import tool.AsyncCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Реализация класса {@link DataRepository} из модуля domain.
 */
public class DataRepositoryImpl implements DataRepository {

    @Override
    public void getMessageList(AsyncCallback<ArrayList<Message>> callback, String token, String login) {
        DatabaseSingleton inst = DatabaseSingleton.getInstance();
        ArrayList<Message> messages = inst.getMessageList(token, login);

        if(messages == null || messages.isEmpty()) callback.onFailure(new Exception());
        else callback.onSuccess(messages);
    }

    @Override
    public void getFriendList(AsyncCallback<ArrayList<User>> callback, String token) {
        DatabaseSingleton inst = DatabaseSingleton.getInstance();
        ArrayList<User> users = inst.getFriendList(token);

        if(users != null) callback.onSuccess(users);
        else callback.onFailure(new Exception());
    }

    @Override
    public void getPublicMessageList(AsyncCallback<ArrayList<PublicMessage>> callback, String token, String login) {
        DatabaseSingleton inst = DatabaseSingleton.getInstance();
        ArrayList<PublicMessage> messages = inst.getPublicMessageList(token, login);

        if(messages == null || messages.isEmpty()) callback.onFailure(new Exception());
        else callback.onSuccess(messages);
    }

    @Override
    public void getRankedMessageList(AsyncCallback<List<Word>> callback, String token) {
        DatabaseSingleton inst = DatabaseSingleton.getInstance();
        List<Word> words = inst.getRankedMessageList(token);
        if(words == null || words.isEmpty()) callback.onFailure(new Exception());
        else callback.onSuccess(words);
    }
}
