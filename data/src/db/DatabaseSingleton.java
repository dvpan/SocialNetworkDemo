package db;

import db.model.Friend;
import model.Message;
import model.PublicMessage;
import model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Эмулятор удаленного сервера.
 */
public class DatabaseSingleton{
    private Database database;
    private static DatabaseSingleton ourInstance = new DatabaseSingleton();

    public static DatabaseSingleton getInstance() {
        return ourInstance;
    }

    private DatabaseSingleton() {
        database = new Database();
        database.setUsers(createFakeUsers());
        database.setFriends(new ArrayList<>());
        database.setTokens(new HashMap());
        database.setMessages(new ArrayList<>());
        database.setPublicMessages(new ArrayList<>());
    }

    private List<User> createFakeUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("alex nav", "alex", 123).setId(0));
        users.add(new User("tan vah", "tan", 123).setId(1));
        users.add(new User("grish mako", "grish", 123).setId(2));
        users.add(new User("dan pad", "dan", 48690).setId(3)); //123

        return users;
    }

    public Boolean signUp(String name, String login, String password) {
        Boolean response = !database.loginExist(login);
        if(response)
            database.getUsers().add(new User(name, login, password.hashCode()).setId(database.getUsers().size()));
        return response;
    }

    public String signIn(String login, Integer hashPass) {
        String token = "ERROR";
        for(User user : database.getUsers())
            if(user.getLogin().equals(login) && user.getPassword().equals(hashPass)){
                token = database.newActiveUser(user);
                return token;
            }
        return token;
    }

    public Boolean addFriend(String token, String login) {
        User user1 = database.getActiveUser(token);
        User user2 = database.getUser(login);
        if(user1 == null || user2 == null) return false;

        Integer user_id = user1.getId();
        Integer friend_id = user2.getId();

        Friend friend = new Friend(user_id, friend_id);
        return database.addFriend(friend);
    }

    public ArrayList<User> getFriendList(String token) {
        User owner = database.getActiveUser(token);
        if(owner == null) return null;

        ArrayList<User> users = new ArrayList<>();
        for(Friend friend : database.getFriends()){
            if(friend.getUserId().equals(owner.getId()))
                users.add(database.getUser(friend.getFriendId()));
        }

        return users;
    }

    public Boolean sendMessage(String token, String login, String text) {
        User user1 = database.getActiveUser(token);
        User user2 = database.getUser(login);
        if(user1 == null || user2 == null || text == null || text.trim().equals("")) return false;

        Integer from_id = user1.getId();
        Integer to_id = user2.getId();

        Message message = new Message(from_id, to_id, text, new Date().getTime());
        return database.sendMessage(message);
    }

    public ArrayList<Message> getMessageList(String token, String login) {
        User user1 = database.getActiveUser(token);
        User user2 = database.getUser(login);
        if(user1 == null || user2 == null) return null;

        ArrayList<Message> messages = new ArrayList<>();
        for(Message message : database.getMessages()){
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
        User user = database.getActiveUser(token);
        if(user == null ||  text == null || text.trim().equals("")) return false;

        Integer from_id = user.getId();

        PublicMessage message = new PublicMessage(from_id, text, new Date().getTime());
        return database.sendPublicMessage(message);
    }

    public ArrayList<PublicMessage> getPublicMessageList(String token, String login) {
        User user = database.getActiveUser(token);
        if(user == null) return null;

        ArrayList<PublicMessage> messages = new ArrayList<>();
        for(PublicMessage message : database.getPublicMessages()){
            Integer user_id = message.getUserId();
            if(user_id.equals(user.getId()))
                messages.add(new PublicMessage(message.getText(), message.getDate()));
        }
        return messages;
    }
}