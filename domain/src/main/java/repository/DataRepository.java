package repository;

import model.Message;
import model.PublicMessage;
import model.User;
import model.Word;
import tool.AsyncCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Интерфейс репозитория, объявляющий методы работы с ним.
 * Содержит действия, которые связаны с получением данных.
 */
public interface DataRepository {
    void getMessageList(AsyncCallback<ArrayList<Message>> callback, String token, String login);
    void getFriendList(AsyncCallback<ArrayList<User>> callback, String token);
    void getPublicMessageList(AsyncCallback<ArrayList<PublicMessage>> callback, String token, String login);
    void getRankedMessageList(AsyncCallback<List<Word>> callback, String token);
}
