package repository;

import model.Message;
import model.Word;
import tool.AsyncCallback;

import java.util.ArrayList;
import java.util.List;

public interface MessageRepository {
    void sendMessage(AsyncCallback<Void> callback, String token, String login, String text);
    void getMessageList(AsyncCallback<ArrayList<Message>> callback, String token, String login);
    void getRankedMessageList(AsyncCallback<List<Word>> callback, String token);
}
