package repository.local;

import db.DatabaseSingleton;
import model.Message;
import model.Word;
import repository.MessageRepository;
import tool.AsyncCallback;

import java.util.ArrayList;
import java.util.List;

public class MessageRepositoryImpl implements MessageRepository {
    @Override
    public void sendMessage(AsyncCallback<Void> callback, String token, String login, String text) {
        DatabaseSingleton inst = DatabaseSingleton.getInstance();
        Boolean response = inst.sendMessage(token, login, text);
        if(response) callback.onSuccess();
        else callback.onFailure(new Exception());
    }

    @Override
    public void getMessageList(AsyncCallback<ArrayList<Message>> callback, String token, String login) {
        DatabaseSingleton inst = DatabaseSingleton.getInstance();
        ArrayList<Message> messages = inst.getMessageList(token, login);
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
