package usecase;

import base.UseCase;
import model.Token;
import model.User;
import repository.DataRepository;
import tool.AsyncCallback;

import java.util.ArrayList;

/**
 * UseCase, предназначенный для получения списка "друзей" авторизованного пользователя.
 */
public class FriendListGet extends UseCase<ArrayList<User>, FriendListGet.Params> {
    private final DataRepository mRepository;

    public FriendListGet(DataRepository repository) {
        mRepository = repository;
    }

    @Override
    protected void createAsyncUseCase(AsyncCallback<ArrayList<User>> callback, FriendListGet.Params params) {
        mRepository.getFriendList(callback, params.token, params.owner);
    }

    public static final class Params{
        private Token token;
        private User owner;

        public Params(Token token, User sendFrom) {
            this.token = token;
            this.owner = owner;
        }
    }
}