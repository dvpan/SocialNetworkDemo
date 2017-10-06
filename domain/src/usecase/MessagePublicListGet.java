package usecase;

import base.UseCase;
import model.PublicMessage;
import repository.DataRepository;
import tool.AsyncCallback;

import java.util.ArrayList;


public class MessagePublicListGet extends UseCase<ArrayList<PublicMessage>, MessagePublicListGet.Params>{
    private final DataRepository mRepository;

    public MessagePublicListGet(DataRepository repository) {
        mRepository = repository;
    }

    @Override
    protected void createAsyncUseCase(AsyncCallback<ArrayList<PublicMessage>> callback, MessagePublicListGet.Params params) {
        mRepository.getPublicMessageList(callback, params.token, params.login);
    }

    public static final class Params{
        private String token;
        private String login;

        public Params(String token, String login) {
            this.token = token;
            this.login = login;
        }
    }
}
