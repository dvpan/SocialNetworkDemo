package usecase;

import base.UseCase;
import model.Token;
import model.User;
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
        mRepository.addToFriendList(callback, params.token, params.sendFrom, params.sendTo);
    }

    public static final class Params{
        private Token token;
        private User sendFrom;
        private User sendTo;

        public Params(Token token, User sendFrom, User sendTo) {
            this.token = token;
            this.sendFrom = sendFrom;
            this.sendTo = sendTo;
        }
    }
}