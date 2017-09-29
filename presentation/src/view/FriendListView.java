package view;

import model.User;
import presenter.FriendListPresenter;

import java.util.ArrayList;

/**
 * Экран, содержащий список друзей пользователя.
 */
public class FriendListView implements FriendListPresenter.View{
    FriendListPresenter presenter;

    public FriendListView(){
        presenter = new FriendListPresenter();
        presenter.setView(this);
        presenter.init();
    }

    @Override
    public void showFriendList(ArrayList<User> users) {
        users.forEach((user)->System.out.print(user.getFirstName()));
    }

    @Override
    public void showError(Throwable caught) {
        System.out.println("Error: " + caught.getMessage());
    }
}
