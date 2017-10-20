package base;

import tool.AsyncCallback;

/**
 * Класс родитель для всех usecase'сов.
 */
public abstract class UseCase<T, Params> {
    public void execute(AsyncCallback<T> callback, Params params) {
        createAsyncUseCase(callback, params);
    }

    protected abstract void createAsyncUseCase(AsyncCallback<T> callback, Params params);
}
