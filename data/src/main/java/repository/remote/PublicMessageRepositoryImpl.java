package repository.remote;

import model.PublicMessage;
import repository.PublicMessageRepository;
import repository.remote.dao.PublicMessageDaoSQL;
import tool.AsyncCallback;

import java.util.ArrayList;
import java.util.Date;

public class PublicMessageRepositoryImpl implements PublicMessageRepository {
    private PublicMessageDaoSQL publicMessageDaoSQL;

    public PublicMessageRepositoryImpl(PublicMessageDaoSQL publicMessageDaoSQL) {
        this.publicMessageDaoSQL = publicMessageDaoSQL;
    }

    @Override
    public void sendPublicMessage(AsyncCallback<Void> callback, String token, String text) {
        Boolean response =
                publicMessageDaoSQL.sendPublicMessage(token, text, new Date().getTime());
        if(response) callback.onSuccess();
        else callback.onFailure(new Exception());
    }

    @Override
    public void getPublicMessageList(AsyncCallback<ArrayList<PublicMessage>> callback, String login) {
        ArrayList <PublicMessage> messages =
                publicMessageDaoSQL.getPublicMessageList(login);

        if(messages == null || messages.isEmpty()) callback.onFailure(new Exception());
        else callback.onSuccess(messages);
    }
}
