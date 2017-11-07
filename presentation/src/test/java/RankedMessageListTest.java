import db.DatabaseSingleton;
import model.Word;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import repository.local.MessageRepositoryImpl;
import repository.local.UserRepositoryImpl;
import tool.AsyncCallback;
import usecase.MessageListRankedGet;
import usecase.MessageSend;
import usecase.UserSignIn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class RankedMessageListTest {
    @Parameterized.Parameter()
    public String messageText;
    @Parameterized.Parameter(1)
    static public List<Word> words;

    private static String token;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        String message1 = "hello hello sup hello sup hello zdorova " +
                "hello hello hi sup hello sup hello zdorova hello";
        List<Word> actualRating1 = new ArrayList<Word>(){{
            add(new Word("hello", 9));
            add(new Word("sup", 4));
            add(new Word("zdorova", 2));
            add(new Word("hi", 1));
        }};

        String message2 = "hello buna chao ave hola namaste hello sup hello " +
                "bonjour zdorova ave hola sup aloha hello zdorova" +
                "hello chao selamat hello hi sup hello sup ola " +
                "selamat hello zdorova ave hola namaste hello shalom";
        List<Word> actualRating2 = new ArrayList<Word>(){{
            add(new Word("hello", 8));
            add(new Word("sup", 4));
            add(new Word("ave", 3));
            add(new Word("hola", 3));
            add(new Word("chao", 2));
            add(new Word("namaste", 2));
            add(new Word("selamat", 2));
            add(new Word("zdorova", 2));
            add(new Word("aloha", 1));
            add(new Word("bonjour", 1));
        }};

        String message3 = "";
        List<Word> actualRating3 = new ArrayList<Word>(){{ }};

        return Arrays.asList(new Object[][]{
                {message1, actualRating1},
                {message2, actualRating2},
                {message3, actualRating3}
        });
    }

    @BeforeClass
    public static void signIn(){
        new UserSignIn(new UserRepositoryImpl()).execute(new AsyncCallback<String>() {
            @Override
            public void onSuccess(String userToken) {
                token = userToken;
            }
        }, new UserSignIn.Params("dan", "123"));
    }

    @Test
    public void testTopWords() {
        sendFakeMessage(token, messageText);
        new MessageListRankedGet(new MessageRepositoryImpl()).execute(new AsyncCallback<List<Word>>() {
            @Override
            public void onSuccess(List<Word> words) {
                Assert.assertEquals(words, RankedMessageListTest.this.words);
            }
        }, token);
    }

    @After
    public void clearMessageList(){
        DatabaseSingleton.getInstance().clearMessageList(token);
    }

    private void sendFakeMessage(String token, String message) {
        new MessageSend(new MessageRepositoryImpl()).execute(new AsyncCallback<>(),
                new MessageSend.Params(token, "tan", message));
    }
}
