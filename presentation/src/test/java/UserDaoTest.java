import exception.InvalidLoginPasswordException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import repository.remote.UserRepositoryImpl;
import repository.remote.dao.UserDaoSQL;
import tool.AsyncCallback;
import usecase.UserSignIn;
import usecase.UserSignUp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoTest {
    UserSignUp userSignUp;
    UserSignIn userSignIn;

    @Mock
    private UserDaoSQL userDaoSQL;

    @Before
    public void setUp() {
        userSignUp = new UserSignUp(new UserRepositoryImpl(userDaoSQL));
        userSignIn = new UserSignIn(new UserRepositoryImpl(userDaoSQL));

        when(userDaoSQL.signUp(anyString(), anyString(), anyInt())).then(invocationOnMock -> {
            String name = invocationOnMock.getArgumentAt(0, String.class);
            String login = invocationOnMock.getArgumentAt(1, String.class);
            if(name.isEmpty() || name.trim().length() > 25) return false;
            if(login.isEmpty() || login.trim().length() > 15 || login.trim().length() < 4) return false;
            return true;
        });

        when(userDaoSQL.signIn(anyString(), anyInt(), anyString())).then(invocationOnMock -> {
            String login = invocationOnMock.getArgumentAt(0, String.class);
            String token = invocationOnMock.getArgumentAt(2, String.class);
            if(login.isEmpty() || login.trim().length() > 15 || login.trim().length() < 2) throw new InvalidLoginPasswordException();
            return token;
        });
    }

    @Test(expected = InvalidLoginPasswordException.class)
    public void takenUsernameRaisesException() {
        userSignIn.execute(new AsyncCallback<>(),
                new UserSignIn.Params("d", "d"));
    }

    @Test
    public void addFriend() {
        userSignUp.execute(new AsyncCallback<>(),
                new UserSignUp.Params("name", "login", "password"));
        ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> loginCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> passCaptor = ArgumentCaptor.forClass(Integer.class);

        verify(userDaoSQL, times(1)).
                signUp(nameCaptor.capture(), loginCaptor.capture(), passCaptor.capture());

        assertEquals("login", loginCaptor.getValue());
        assertEquals((Integer) "password".hashCode(), passCaptor.getValue());
    }
}
