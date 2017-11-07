package usecase;

import base.UseCase;
import model.PublicMessage;
import repository.PublicMessageRepository;
import tool.AsyncCallback;

import java.util.ArrayList;


public class MessagePublicListGet extends UseCase<ArrayList<PublicMessage>, MessagePublicListGet.Params>{
    private final PublicMessageRepository mRepository;

    public MessagePublicListGet(PublicMessageRepository repository) {
        mRepository = repository;
    }

    @Override
    protected void createAsyncUseCase(AsyncCallback<ArrayList<PublicMessage>> callback, MessagePublicListGet.Params params) {
        mRepository.getPublicMessageList(callback, params.login);
    }

    public static final class Params{
        private String login;

        public Params(String login) {
            this.login = login;
        }
    }
}
