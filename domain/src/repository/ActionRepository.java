package repository;

import tool.AsyncCallback;
import usecase.UserSignIn;
import usecase.UserSignUp;

/**
 * Интерфейс репозитория, объявляющий методы работы с ним.
 * Содержит действия, которые не связаны с получением данных.
 */
public interface ActionRepository {
    void signUp(AsyncCallback<Boolean> callback, UserSignUp.Params params);
    void signIn(AsyncCallback<String> callback, UserSignIn.Params params);
    void addToFriendList(AsyncCallback<Boolean> callback, String token, String login);
    void sendMessage(AsyncCallback<Void> callback, String token, String login, String text);
    void sendPublicMessage(AsyncCallback<Void> callback, String token, String text);
}
