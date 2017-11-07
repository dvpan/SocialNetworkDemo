package usecase;

import base.UseCase;
import repository.UserRepository;
import tool.AsyncCallback;

/**
 * UseCase, авторизации пользователя. Если она будет успешной,
 * то он получит токен авторизации.
 */
public class UserSignIn extends UseCase<String, UserSignIn.Params> {
    private final UserRepository mRepository;

    public UserSignIn(UserRepository repository) {
        mRepository = repository;
    }

    @Override
    protected void createAsyncUseCase(AsyncCallback<String> callback, Params params) {
        mRepository.signIn(callback, params);
    }

    public static final class Params{
        private String login;
        private String password;

        public Params(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }
    }
}
