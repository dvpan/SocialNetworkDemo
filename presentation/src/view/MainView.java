package view;

import model.Message;
import model.PublicMessage;
import model.User;
import presenter.MainPresenter;
import tool.exception.InputCanceledException;

import java.util.ArrayList;

public class MainView extends BaseView implements MainPresenter.View {
    MainPresenter presenter;

    MainView(){
        presenter = new MainPresenter();
        presenter.setView(this);
        presenter.init();
        render();
    }

    @Override
    public void render() {
        printTitle("Main Menu");
        presenter.menuItemEnter(printMenuAndWait(
                "New Friend", "My Friends",
                "New Message", "My Messages",
                "New Public Message", "Read Public Messages",
                "Log Out"));
    }

    @Override
    public void inputCanceled() {
        print("Input was cancel!");
    }

    @Override
    public void renderNewFriend() throws InputCanceledException {
        printTitle("New Friend");
        print("User's login: ");
        String login = getLine();
        presenter.newFriend(login);
    }

    @Override
    public void renderFriendList() {
        printTitle("My Friends");
        presenter.getFriendList();
    }

    @Override
    public void renderNewMessage() throws InputCanceledException {
        printTitle("New Message");
        print("Receiver login: ");
        String login = getLine();
        print("Message's text: ");
        String text = getLine();
        presenter.newMessage(login, text);
    }

    @Override
    public void renderMessageList() throws InputCanceledException {
        printTitle("My Messages");
        print("Receiver login: ");
        String login = getLine();
        presenter.getMessageList(login);
    }

    @Override
    public void renderNewPublicMessage() throws InputCanceledException {
        printTitle("New Public Message");
        print("Message's text: ");
        String text = getLine();
        presenter.newPublicMessage(text);
    }


    @Override
    public void renderPublicMessageList() throws InputCanceledException {
        printTitle("Public Messages");
        print("User's login: ");
        String login = getLine();
        presenter.getPublicMessageList(login);
    }

    @Override
    public void showPublicMessageList(ArrayList<PublicMessage> messages) {
        println("Public Message List: ");
        messages.forEach(message -> println(
                message.getDate() + ":\n" + message.getText()));
    }

    @Override
    public void showInfoMessage(String message) {
        println(message);
    }

    @Override
    public void showFriendList(ArrayList<User> users) {
        println("Friend List: ");
        users.forEach(user -> println(
                "Name: " + user.getName() + ", Login: " + user.getLogin()));

    }

    @Override
    public void showMessageList(ArrayList<Message> messages) {
        println("Message List: ");
        messages.forEach(message -> println(
                message.getUserFromName() + ":\n" + message.getText()));
    }
}
