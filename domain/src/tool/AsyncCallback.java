package tool;

/**
 * Основной интерфейс, который должна реализовать вызывающая сторона,
 * для того чтобы получить ответ от процедуры, вызванной удаленно.
 */
public abstract class AsyncCallback<T> {
    public void onSuccess(T obj){}
    public void onFailure(Throwable caught){}
}
