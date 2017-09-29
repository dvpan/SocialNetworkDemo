package usecase;

import base.UseCase;
import model.Message;
import model.Token;
import model.User;
import repository.DataRepository;
import tool.AsyncCallback;

import java.util.ArrayList;

/**
 * UseCase, предназначенный для получения списка сообщений авторизованного пользователя.
 */
public class MessageListGet extends UseCase<ArrayList<Message>, MessageListGet.Params> {
    private final DataRepository mRepository;

    public MessageListGet(DataRepository repository) {
        mRepository = repository;
    }

    @Override
    protected void createAsyncUseCase(AsyncCallback<ArrayList<Message>> callback, Params params) {
        mRepository.getMessageList(callback, params.token, params.owner);
    }

    public static final class Params{
        private Token token;
        private User owner;

        public Params(Token token, User sendFrom) {
            this.token = token;
            this.owner = owner;
        }
    }
}