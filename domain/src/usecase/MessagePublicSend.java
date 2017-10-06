package usecase;

import base.UseCase;
import repository.ActionRepository;
import tool.AsyncCallback;

public class MessagePublicSend extends UseCase<Void, MessagePublicSend.Params>{
    private final ActionRepository mRepository;

    public MessagePublicSend(ActionRepository repository) {
        mRepository = repository;
    }

    @Override
    protected void createAsyncUseCase(AsyncCallback<Void> callback, MessagePublicSend.Params params) {
        mRepository.sendPublicMessage(callback, params.token, params.text);
    }

    public static final class Params{
        private String token;
        private String text;

        public Params(String token, String text) {
            this.token = token;
            this.text = text;
        }
    }
}
