package presenter;

import repository.ActionRepositoryImpl;
import tool.AsyncCallback;
import tool.exception.InputCanceledException;
import usecase.UserSignIn;
import usecase.UserSignUp;

import static tool.StaticRepository.SESSION_TOKEN;


public class UserEnterPresenter extends Presenter<UserEnterPresenter.View>{

    UserSignIn userSignIn;
    UserSignUp userSignUp;

    public UserEnterPresenter() {
        this.userSignIn = new UserSignIn(new ActionRepositoryImpl());
        this.userSignUp = new UserSignUp(new ActionRepositoryImpl());
    }

    @Override
    public void init() {

    }

    public void signUp(String name, String login, String password) {
        userSignUp.execute(new AsyncCallback<Boolean>() {
            @Override
            public void onSuccess() {
                getView().signUpSuccess();
            }

            @Override
            public void onFailure(Throwable caught) {
                getView().signUpFailure();
            }
        }, new UserSignUp.Params(name, login, password));
    }

    public void signIn(String login, String password) {
        userSignIn.execute(new AsyncCallback<String>() {
            @Override
            public void onSuccess(String token) {
                SESSION_TOKEN = token;
                getView().signInSuccess();
            }

            @Override
            public void onFailure(Throwable caught){
                getView().signInFailure();
            }

        }, new UserSignIn.Params(login, password));
    }

    public void menuItemEnter(int i) {
        try {
            switch (i){
                case 1: getView().renderSignUp(); break;
                case 2: getView().renderSignIn(); break;
                case 0: System.exit(0);
            }
        } catch (InputCanceledException e){
            getView().inputCanceled();
            getView().render();
        }
    }

    public interface View extends Presenter.View{
        void signUpSuccess();
        void signInSuccess();
        void signUpFailure();
        void signInFailure();

        void render();
        void renderSignUp() throws InputCanceledException;
        void renderSignIn() throws InputCanceledException;

        void inputCanceled();
    }
}
