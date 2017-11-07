package repository.remote;

import model.User;
import repository.FriendRepository;
import repository.remote.dao.FriendDaoSQL;
import tool.AsyncCallback;

import java.util.ArrayList;

public class FriendRepositoryImpl implements FriendRepository {
    private FriendDaoSQL friendDaoSQL;

    public FriendRepositoryImpl(FriendDaoSQL messageDaoSQL) {
        this.friendDaoSQL = messageDaoSQL;
    }

    @Override
    public void addToFriendList(AsyncCallback<Boolean> callback, String token, String login) {
        Boolean response = friendDaoSQL.addFriend(token, login);
        if(response) callback.onSuccess();
        else callback.onFailure(new Exception());
    }

    @Override
    public void getFriendList(AsyncCallback<ArrayList<User>> callback, String token) {
        ArrayList<User> users =
                friendDaoSQL.getFriendList(token);
        if(users != null) callback.onSuccess(users);
        else callback.onFailure(new Exception());
    }
}
