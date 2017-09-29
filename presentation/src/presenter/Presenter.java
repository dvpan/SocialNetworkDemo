package presenter;

/**
 * Абстрактный класс, представляющий презентер в паттерне MVP (model, view, presenter).
 */
public abstract class Presenter <T extends Presenter.View>{
    T view;
    public void setView(T view){
        this.view = view;
    }

    public T getView(){
        return view;
    }


    /**
     * Интерфейс, содержащий методы работы с View.
     */
    public interface View{

    }
}
