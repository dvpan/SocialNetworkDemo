package repository;

import model.Message;
import model.Token;
import model.User;
import tool.AsyncCallback;

import java.util.ArrayList;

/**
 * Заглушка, представляющая собой реализацию класса {@link DataRepository} из модуля domain.
 */
public class DataRepositoryImpl implements DataRepository {
    @Override
    public void sendMessage(AsyncCallback<Boolean> callback, Token token, User sendFrom, User sendTo) {

    }

    @Override
    public void getMessageList(AsyncCallback<ArrayList<Message>> callback, Token token, User owner) {

    }

    @Override
    public void getFriendList(AsyncCallback<ArrayList<User>> callback, Token token, User owner) {

    }
}
