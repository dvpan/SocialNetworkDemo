package repository.local;

import db.DatabaseSingleton;
import repository.UserRepository;
import tool.AsyncCallback;
import usecase.UserSignIn;
import usecase.UserSignUp;

public class UserRepositoryImpl implements UserRepository{
    @Override
    public void signUp(AsyncCallback<Boolean> callback, UserSignUp.Params params) {
        DatabaseSingleton inst = DatabaseSingleton.getInstance();
        Boolean response = inst.signUp(params.getName(), params.getLogin(), params.getPassword());
        if(response) callback.onSuccess();
        else callback.onFailure(new Exception());
    }

    @Override
    public void signIn(AsyncCallback<String> callback, UserSignIn.Params params) {
        DatabaseSingleton inst = DatabaseSingleton.getInstance();
        String token = inst.signIn(params.getLogin(), params.getPassword().hashCode());
        if(!token.equals("ERROR")) callback.onSuccess(token);
        else callback.onFailure(new Exception());
    }
}
