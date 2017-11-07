package repository;

import model.PublicMessage;
import tool.AsyncCallback;

import java.util.ArrayList;

public interface PublicMessageRepository {
    void sendPublicMessage(AsyncCallback<Void> callback, String token, String text);
    void getPublicMessageList(AsyncCallback<ArrayList<PublicMessage>> callback, String login);

}
