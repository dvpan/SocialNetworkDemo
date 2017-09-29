package usecase;

import base.UseCase;
import model.Token;
import model.User;
import repository.ActionRepository;
import tool.AsyncCallback;

/**
 * UseCase, авторизации пользователя. Если она будет успешной,
 * то он получит токен авторизации.
 */
public class UserLogin extends UseCase<Token, User> {
    private final ActionRepository mRepository;

    public UserLogin(ActionRepository repository) {
        mRepository = repository;
    }

    @Override
    protected void createAsyncUseCase(AsyncCallback<Token> callback, User user) {
        mRepository.login(callback, user);
    }
}
