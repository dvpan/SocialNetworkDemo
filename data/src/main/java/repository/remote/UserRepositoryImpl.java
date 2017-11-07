package repository.remote;

import repository.UserRepository;
import repository.remote.dao.UserDaoSQL;
import tool.AsyncCallback;
import usecase.UserSignIn;
import usecase.UserSignUp;

import java.util.Random;

public class UserRepositoryImpl implements UserRepository {
    private UserDaoSQL userDaoSQL;

    public UserRepositoryImpl(UserDaoSQL userDaoSQL) {
        this.userDaoSQL = userDaoSQL;
    }

    @Override
    public void signUp(AsyncCallback<Boolean> callback, UserSignUp.Params params) {
        Boolean response = userDaoSQL.signUp(params.getName(), params.getLogin(), params.getPassword().hashCode());
        if(response) callback.onSuccess();
        else callback.onFailure(new Exception());
    }

    @Override
    public void signIn(AsyncCallback<String> callback, UserSignIn.Params params) {
        String token = userDaoSQL.signIn(params.getLogin(), params.getPassword().hashCode(), createToken(params.getLogin()));
        if(!token.equals("ERROR")) callback.onSuccess(token);
        else callback.onFailure(new Exception());
    }

    String createToken(String login) {
        return String.valueOf(login.hashCode())
                + String.valueOf(new Random().nextInt());
    }
}
