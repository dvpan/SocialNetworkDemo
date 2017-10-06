package repository;

import model.Message;
import model.PublicMessage;
import model.User;
import tool.AsyncCallback;

import java.util.ArrayList;

/**
 * Интерфейс репозитория, объявляющий методы работы с ним.
 * Содержит действия, которые связаны с получением данных.
 */
public interface DataRepository {
    void getMessageList(AsyncCallback<ArrayList<Message>> callback, String token, String login);
    void getFriendList(AsyncCallback<ArrayList<User>> callback, String token);

    void getPublicMessageList(AsyncCallback<ArrayList<PublicMessage>> callback, String token, String login);
}
