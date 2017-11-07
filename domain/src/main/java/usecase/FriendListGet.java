package usecase;

import base.UseCase;
import model.User;
import repository.FriendRepository;
import tool.AsyncCallback;

import java.util.ArrayList;

/**
 * UseCase, предназначенный для получения списка "друзей" авторизованного пользователя.
 */
public class FriendListGet extends UseCase<ArrayList<User>, String> {
    private final FriendRepository mRepository;

    public FriendListGet(FriendRepository repository) {
        mRepository = repository;
    }

    @Override
    protected void createAsyncUseCase(AsyncCallback<ArrayList<User>> callback, String token) {
        mRepository.getFriendList(callback, token);
    }
}