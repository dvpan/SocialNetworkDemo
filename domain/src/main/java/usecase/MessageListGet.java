package usecase;

import base.UseCase;
import model.Message;
import repository.MessageRepository;
import tool.AsyncCallback;

import java.util.ArrayList;

/**
 * UseCase, предназначенный для получения списка сообщений авторизованного пользователя.
 */
public class MessageListGet extends UseCase<ArrayList<Message>, MessageListGet.Params> {
    private final MessageRepository mRepository;

    public MessageListGet(MessageRepository repository) {
        mRepository = repository;
    }

    @Override
    protected void createAsyncUseCase(AsyncCallback<ArrayList<Message>> callback, Params params) {
        mRepository.getMessageList(callback, params.token, params.login);
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