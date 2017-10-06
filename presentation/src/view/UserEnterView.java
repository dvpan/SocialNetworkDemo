package view;

import presenter.UserEnterPresenter;
import tool.exception.InputCanceledException;

public class UserEnterView extends BaseView implements UserEnterPresenter.View{
    UserEnterPresenter presenter;

    public UserEnterView(){
        presenter = new UserEnterPresenter();
        presenter.setView(this);
        presenter.init();

        render();
    }

    @Override
    public void render() {
        printTitle("Awesome Social Network");
        presenter.menuItemEnter(printMenuAndWait("Sign Up", "Sign In", "Exit"));
    }

    @Override
    public void renderSignUp() throws InputCanceledException {
        printTitle("Sign Up");
        printHelper();
        print("Enter your name: ");
        String name = getLine();
        print("Enter your login: ");
        String login = getLine();
        print("Enter your password: ");
        String password = getLine();

        presenter.signUp(name, login, password);
    }

    @Override
    public void renderSignIn() throws InputCanceledException {
        printTitle("Sign In");
        printHelper();
        print("Enter your login: ");
        String login = getLine();
        print("Enter your password: ");
        String password = getLine();

        presenter.signIn(login, password);
    }

    @Override
    public void inputCanceled() {
        print("Input was cancel!");
    }

    @Override
    public void signUpSuccess() {
        println("Registration Success!");
        render();
    }

    @Override
    public void signInSuccess() {
        println("Login Success!");
        new MainView();
        render();
    }

    @Override
    public void signUpFailure() {
        println("Registration Failure!");
        render();
    }

    @Override
    public void signInFailure(){
        println("Login Failure!");
        render();
    }
}
