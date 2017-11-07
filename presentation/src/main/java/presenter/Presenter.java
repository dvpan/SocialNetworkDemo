package presenter;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Абстрактный класс, представляющий презентер в паттерне MVP (model, view, presenter).
 */
public abstract class Presenter <T extends Presenter.View>{
    ClassPathXmlApplicationContext appContext =
            new ClassPathXmlApplicationContext("/application-context.xml");

    T view;
    public void setView(T view){
        this.view = view;
    }

    public T getView(){
        return view;
    }

    public void init(){}

    /**
     * Интерфейс, содержащий методы работы с View.
     */
    public interface View{

    }
}
