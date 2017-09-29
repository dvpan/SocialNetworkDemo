package repository;

import model.Token;
import model.User;
import tool.AsyncCallback;

/**
 * Интерфейс репозитория, объявляющий методы работы с ним.
 * Содержит действия, которые не связаны с получением данных.
 */
public interface ActionRepository {
    void registration(AsyncCallback<Boolean> callback, User user);
    void login(AsyncCallback<Token> callback, User user);
    void addToFriendList(AsyncCallback<Boolean> callback, Token token, User sendFrom, User sendTo);
}
