package usecase;

import base.UseCase;
import repository.ActionRepository;
import tool.AsyncCallback;

/**
 * UseCase, предназначенный для отправления сообщения
 * от авторизованного пользователя другому пользователю.
 */
public class MessageSend extends UseCase<Void, MessageSend.Params> {
    private final ActionRepository mRepository;

    public MessageSend(ActionRepository repository) {
        mRepository = repository;
    }

    @Override
    protected void createAsyncUseCase(AsyncCallback<Void> callback, Params params) {
        mRepository.sendMessage(callback, params.token, params.login, params.text);
    }

    public static final class Params{
        private String token;
        private String login;
        private String text;

        public Params(String token, String login, String text) {
            this.token = token;
            this.login = login;
            this.text = text;
        }
    }
}
