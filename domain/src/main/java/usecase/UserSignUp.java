package usecase;

import base.UseCase;
import repository.UserRepository;
import tool.AsyncCallback;

/**
 * UseCase, предназначенный регистрации пользователя.
 */
public class UserSignUp extends UseCase<Boolean, UserSignUp.Params> {
    private final UserRepository mRepository;

    public UserSignUp(UserRepository repository) {
        mRepository = repository;
    }

    @Override
    protected void createAsyncUseCase(AsyncCallback<Boolean> callback, Params params) {
        mRepository.signUp(callback, params);
    }

    public static final class Params{
        private String name;
        private String login;
        private String password;

        public Params(String name, String login, String password) {
            this.name = name;
            this.login = login;
            this.password = password;
        }

        public String getName() {return name;}

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }

    }
}
