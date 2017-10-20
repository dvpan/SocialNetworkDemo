package db;

import db.model.Friend;
import model.Message;
import model.PublicMessage;
import model.User;
import model.Word;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Эмулятор удаленного сервера.
 */
public class DatabaseSingleton{
    private DatabaseStorage databaseStorage;
    private DatabaseProcess databaseProcess;
    private static DatabaseSingleton ourInstance = new DatabaseSingleton();

    public static DatabaseSingleton getInstance() {
        return ourInstance;
    }

    private DatabaseSingleton() {
        databaseProcess = new DatabaseProcess();

        databaseStorage = new DatabaseStorage();
        databaseStorage.setUsers(createFakeUsers());
        databaseStorage.setFriends(new ArrayList<>());
        databaseStorage.setTokens(new HashMap());
        databaseStorage.setMessages(new ArrayList<>());
        databaseStorage.setPublicMessages(new ArrayList<>());
    }

    private List<User> createFakeUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("alex nav", "alex", 123).setId(0));
        users.add(new User("tan vah", "tan", 123).setId(1));
        users.add(new User("grish mako", "grish", 123).setId(2));
        users.add(new User("dan pad", "dan", 48690).setId(3)); // 123

        return users;
    }

    public Boolean signUp(String name, String login, String password) {
        Boolean response = !databaseStorage.loginExist(login);
        if(response)
            databaseStorage.getUsers().add(new User(name, login, password.hashCode()).setId(databaseStorage.getUsers().size()));
        return response;
    }

    public String signIn(String login, Integer hashPass) {
        String token = "ERROR";
        for(User user : databaseStorage.getUsers())
            if(user.getLogin().equals(login) && user.getPassword().equals(hashPass)){
                token = databaseStorage.newActiveUser(user);
                return token;
            }
        return token;
    }

    public Boolean addFriend(String token, String login) {
        User user1 = databaseStorage.getActiveUser(token);
        User user2 = databaseStorage.getUser(login);
        if(user1 == null || user2 == null) return false;

        Integer user_id = user1.getId();
        Integer friend_id = user2.getId();

        Friend friend = new Friend(user_id, friend_id);
        return databaseStorage.addFriend(friend);
    }

    public ArrayList<User> getFriendList(String token) {
        User owner = databaseStorage.getActiveUser(token);
        if(owner == null) return null;

        ArrayList<User> users = new ArrayList<>();
        for(Friend friend : databaseStorage.getFriends()){
            if(friend.getUserId().equals(owner.getId()))
                users.add(databaseStorage.getUser(friend.getFriendId()));
        }

        return users;
    }

    public Boolean sendMessage(String token, String login, String text) {
        User user1 = databaseStorage.getActiveUser(token);
        User user2 = databaseStorage.getUser(login);
        if(user1 == null || user2 == null || text == null || text.trim().equals("")) return false;

        Integer from_id = user1.getId();
        Integer to_id = user2.getId();

        Message message = new Message(from_id, to_id, text, new Date().getTime());
        return databaseStorage.sendMessage(message);
    }

    public ArrayList<Message> getMessageList(String token, String login) {
        User user1 = databaseStorage.getActiveUser(token);
        User user2 = databaseStorage.getUser(login);
        if(user1 == null || user2 == null) return null;

        ArrayList<Message> messages = new ArrayList<>();
        for(Message message : databaseStorage.getMessages()){
            Integer from_id = message.getUserFromId();
            Integer to_id = message.getUserToId();
            if(from_id.equals(user1.getId()) || to_id.equals(user2.getId()))
                messages.add(new Message(message.getText(),
                        user1.getName(), user2.getName(), message.getDate()));
            else
            if(from_id.equals(user2.getId()) || to_id.equals(user1.getId()))
                messages.add(new Message(message.getText(),
                        user2.getName(), user1.getName(), message.getDate()));
        }

        return messages;
    }

    public Boolean sendPublicMessage(String token, String text) {
        User user = databaseStorage.getActiveUser(token);
        if(user == null ||  text == null || text.trim().equals("")) return false;

        Integer from_id = user.getId();

        PublicMessage message = new PublicMessage(from_id, text, new Date().getTime());
        return databaseStorage.sendPublicMessage(message);
    }

    public ArrayList<PublicMessage> getPublicMessageList(String token, String login) {
        User user = databaseStorage.getActiveUser(token);
        if(user == null) return null;

        ArrayList<PublicMessage> messages = new ArrayList<>();
        for(PublicMessage message : databaseStorage.getPublicMessages()){
            Integer user_id = message.getUserId();
            if(user_id.equals(user.getId()))
                messages.add(new PublicMessage(message.getText(), message.getDate()));
        }
        return messages;
    }

    public List<Word> getRankedMessageList(String token) {
        User user = databaseStorage.getActiveUser(token);
        if(user == null) return null;

        List<Word> words = databaseProcess.getRankedMessageList(databaseStorage.getMessages(), user);

        return words;
    }

    public void clearMessageList(String token) {
        User user = databaseStorage.getActiveUser(token);
        if(user.getLogin().equals("dan")) databaseStorage.getMessages().clear();
    }
}