package repository;

import model.User;
import tool.AsyncCallback;

import java.util.ArrayList;

public interface FriendRepository {
    void addToFriendList(AsyncCallback<Boolean> callback, String token, String login);
    void getFriendList(AsyncCallback<ArrayList<User>> callback, String token);
}
