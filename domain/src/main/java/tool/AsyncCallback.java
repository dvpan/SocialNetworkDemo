package tool;

/**
 * Основной интерфейс, который должна реализовать вызывающая сторона,
 * для того чтобы получить ответ от процедуры, вызванной удаленно.
 */
public class AsyncCallback<T> {
    public void onSuccess(){}
    public void onSuccess(T obj){}
    public void onFailure(Throwable caught){}
}

