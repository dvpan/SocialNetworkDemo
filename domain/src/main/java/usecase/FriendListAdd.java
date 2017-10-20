package usecase;

import base.UseCase;
import repository.ActionRepository;
import tool.AsyncCallback;


/**
 * UseCase, предназначенный для добавления другого
 * пользователя в "друзья" авторизованного пользователя.
 */
public class FriendListAdd extends UseCase<Boolean, FriendListAdd.Params> {
    private final ActionRepository mRepository;

    public FriendListAdd(ActionRepository repository) {
        mRepository = repository;
    }

    @Override
    protected void createAsyncUseCase(AsyncCallback<Boolean> callback, FriendListAdd.Params params) {
        mRepository.addToFriendList(callback, params.token, params.login);
    }

    public static final class Params{
        private String token;
        private String login;

        public Params(String token, String login) {
            this.token = token;
            this.login = login;
        }
    }
}