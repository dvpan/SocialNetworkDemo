package usecase;

import base.UseCase;
import model.Word;
import repository.DataRepository;
import tool.AsyncCallback;

import java.util.List;

public class MessageListRankedGet extends UseCase<List<Word>, String> {

    private DataRepository mRepository;

    public MessageListRankedGet(DataRepository mRepository) {
        this.mRepository = mRepository;
    }

    @Override
    protected void createAsyncUseCase(AsyncCallback<List<Word>> callback, String token) {
        mRepository.getRankedMessageList(callback, token);
    }
}
