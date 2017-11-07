package repository.local;

import db.DatabaseSingleton;
import model.User;
import repository.FriendRepository;
import tool.AsyncCallback;

import java.util.ArrayList;

public class FriendRepositoryImpl implements FriendRepository {

    @Override
    public void addToFriendList(AsyncCallback<Boolean> callback, String token, String login) {
        DatabaseSingleton inst = DatabaseSingleton.getInstance();
        Boolean response = inst.addFriend(token, login);
        if(response) callback.onSuccess();
        else callback.onFailure(new Exception());
    }

    @Override
    public void getFriendList(AsyncCallback<ArrayList<User>> callback, String token) {
        DatabaseSingleton inst = DatabaseSingleton.getInstance();
        ArrayList<User> users = inst.getFriendList(token);
        if(users != null) callback.onSuccess(users);
        else callback.onFailure(new Exception());
    }
}
