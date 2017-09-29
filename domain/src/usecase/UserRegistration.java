package usecase;

import base.UseCase;
import model.User;
import repository.ActionRepository;
import tool.AsyncCallback;

/**
 * UseCase, предназначенный регистрации пользователя.
 */
public class UserRegistration extends UseCase<Boolean, User> {
    private final ActionRepository mRepository;

    public UserRegistration(ActionRepository repository) {
        mRepository = repository;
    }

    @Override
    protected void createAsyncUseCase(AsyncCallback<Boolean> callback, User user) {
        mRepository.registration(callback, user);
    }
}
