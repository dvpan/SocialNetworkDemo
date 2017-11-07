package repository;

import tool.AsyncCallback;
import usecase.UserSignIn;
import usecase.UserSignUp;

public interface UserRepository {
    void signUp(AsyncCallback<Boolean> callback, UserSignUp.Params params);
    void signIn(AsyncCallback<String> callback, UserSignIn.Params params);
}
