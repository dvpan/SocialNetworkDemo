package presenter;

import model.User;
import repository.DataRepositoryImpl;
import tool.AsyncCallback;
import usecase.FriendListGet;

import java.util.ArrayList;

/**
 * {@link Presenter} отвечает за коммуникацию между слоем view и models
 * в слое представления (presentation).
 */
public class FriendListPresenter extends Presenter<FriendListPresenter.View>{
    FriendListGet friendListGet;

    public FriendListPresenter(){
        friendListGet = new FriendListGet(new DataRepositoryImpl());
    }

    public void init() {
        friendListGet.execute(new AsyncCallback<ArrayList<User>>() {
            @Override
            public void onSuccess(ArrayList<User> obj) {
                getView().showFriendList(obj);
            }

            @Override
            public void onFailure(Throwable caught) {
                getView().showError(caught);
            }
        }, new FriendListGet.Params(null, null));
    }

    public interface View extends Presenter.View{
        void showFriendList(ArrayList<User> users);
        void showError(Throwable caught);
    }
}
