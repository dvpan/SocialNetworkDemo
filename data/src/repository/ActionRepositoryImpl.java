package repository;

import model.Token;
import model.User;
import tool.AsyncCallback;

/**
 * Заглушка, представляющая собой реализацию класса {@link ActionRepository} из модуля domain.
 */
public class ActionRepositoryImpl implements ActionRepository {
    @Override
    public void registration(AsyncCallback<Boolean> callback, User user) {

    }

    @Override
    public void login(AsyncCallback<Token> callback, User user) {

    }

    @Override
    public void addToFriendList(AsyncCallback<Boolean> callback, Token token, User sendFrom, User sendTo) {

    }
}
