package repository;

import model.Message;
import model.Token;
import model.User;
import tool.AsyncCallback;

import java.util.ArrayList;

/**
 * Интерфейс репозитория, объявляющий методы работы с ним.
 * Содержит действия, которые связаны с получением данных.
 */
public interface DataRepository {
    void sendMessage(AsyncCallback<Boolean> callback, Token token, User sendFrom, User sendTo);
    void getMessageList(AsyncCallback<ArrayList<Message>> callback, Token token, User owner);
    void getFriendList(AsyncCallback<ArrayList<User>> callback, Token token, User owner);
}
