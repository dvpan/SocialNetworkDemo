package usecase;

import base.UseCase;
import model.Token;
import model.User;
import repository.DataRepository;
import tool.AsyncCallback;

/**
 * UseCase, предназначенный для отправления сообщения
 * от авторизованного пользователя другому пользователю.
 */
public class MessageSend extends UseCase<Boolean, MessageSend.Params> {
    private final DataRepository mRepository;

    public MessageSend(DataRepository repository) {
        mRepository = repository;
    }

    @Override
    protected void createAsyncUseCase(AsyncCallback<Boolean> callback, Params params) {
        mRepository.sendMessage(callback, params.token, params.sendFrom, params.sendTo);
    }

    public static final class Params{
        private Token token;
        private User sendFrom;
        private User sendTo;

        public Params(Token token, User sendFrom, User sendTo) {
            this.token = token;
            this.sendFrom = sendFrom;
            this.sendTo = sendTo;
        }
    }
}
