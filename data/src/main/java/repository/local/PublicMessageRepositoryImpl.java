package repository.local;

import db.DatabaseSingleton;
import model.PublicMessage;
import repository.PublicMessageRepository;
import tool.AsyncCallback;

import java.util.ArrayList;

public class PublicMessageRepositoryImpl implements PublicMessageRepository {
    @Override
    public void sendPublicMessage(AsyncCallback<Void> callback, String token, String text) {
        DatabaseSingleton inst = DatabaseSingleton.getInstance();
        Boolean response = inst.sendPublicMessage(token, text);
        if(response) callback.onSuccess();
        else callback.onFailure(new Exception());
    }

    @Override
    public void getPublicMessageList(AsyncCallback<ArrayList<PublicMessage>> callback, String login) {
        DatabaseSingleton inst = DatabaseSingleton.getInstance();
        ArrayList<PublicMessage> messages = inst.getPublicMessageList(login);
        if(messages == null || messages.isEmpty()) callback.onFailure(new Exception());
        else callback.onSuccess(messages);
    }
}
