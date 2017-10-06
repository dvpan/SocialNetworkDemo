package repository;

import db.DatabaseSingleton;
import tool.AsyncCallback;
import usecase.UserSignIn;
import usecase.UserSignUp;

/**
 * Реализация класса {@link ActionRepository} из модуля domain.
 */
public class ActionRepositoryImpl implements ActionRepository {

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

    @Override
    public void addToFriendList(AsyncCallback<Boolean> callback, String token, String login) {
        DatabaseSingleton inst = DatabaseSingleton.getInstance();
        Boolean response = inst.addFriend(token, login);
        if(response) callback.onSuccess();
        else callback.onFailure(new Exception());
    }

    @Override
    public void sendMessage(AsyncCallback<Void> callback, String token, String login, String text) {
        DatabaseSingleton inst = DatabaseSingleton.getInstance();
        Boolean response = inst.sendMessage(token, login, text);
        if(response) callback.onSuccess();
        else callback.onFailure(new Exception());
    }

    @Override
    public void sendPublicMessage(AsyncCallback<Void> callback, String token, String text) {
        DatabaseSingleton inst = DatabaseSingleton.getInstance();
        Boolean response = inst.sendPublicMessage(token, text);
        if(response) callback.onSuccess();
        else callback.onFailure(new Exception());
    }
}
