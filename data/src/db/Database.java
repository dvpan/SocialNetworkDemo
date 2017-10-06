package db;

import db.model.Friend;
import model.Message;
import model.PublicMessage;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class Database {
    private List<Friend> friends;
    private List<User> users;
    private HashMap<String, User> tokens;
    private List<Message> messages;
    private List<PublicMessage> publicMessages;
    
    public Database() {  }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setTokens(HashMap<String, User> tokens) {
        this.tokens = tokens;
    }

    Boolean addFriend(Friend new_friend) {
        for(Friend friend : friends)
            if(friend.equals(new_friend))
                return false;
        friends.add(new_friend);
        return true;
    }

    User getUser(String login){
        for (User user:users)
            if(user.getLogin().equals(login)) return user;
        return null;
    }


    Boolean loginExist(String login){
        for(User user:users)
            if(user.getLogin().equals(login)) return true;
        return false;
    }

    String createToken(String login) {
        return String.valueOf(login.hashCode()) + String.valueOf(new Random().nextInt());
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public List<User> getUsers() {
        return users;
    }

    public User getActiveUser(String token) {
        return tokens.get(token);

    }

    public String newActiveUser(User user) {
        String token = createToken(user.getLogin());
        tokens.put(token, user);
        return token;
    }

    public User getUser(Integer id) {
        for (User user : users)
            if(user.getId().equals(id)) return new User(user.getName(), user.getLogin());
        return null;
    }

    public Boolean sendMessage(Message message) {
        return messages.add(message);
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public Boolean sendPublicMessage(PublicMessage message) {
        return publicMessages.add(message);
    }

    public void setPublicMessages(List<PublicMessage> publicMessages) {
        this.publicMessages = publicMessages;
    }

    public List<PublicMessage> getPublicMessages() {
        return publicMessages;
    }
}
