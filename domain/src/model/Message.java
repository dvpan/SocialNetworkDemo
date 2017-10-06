package model;

/**
 * Класс, представляющий собой сообщения, которые пользователи могут отправлять друг другу.
 */
public class Message {
    private Integer id;
    private Integer userFromId;
    private Integer userToId;

    private String userFromName;
    private String userToame;

    private String text;
    private Long date;



    public Message(Integer id, Integer userFromId, Integer userToId, String text, Long date) {
        this.id = id;
        this.userFromId = userFromId;
        this.userToId = userToId;
        this.text = text;
        this.date = date;
    }

    public Message(Integer userFromId, Integer userToId, String text, Long date) {
        this.userFromId = userFromId;
        this.userToId = userToId;
        this.text = text;
        this.date = date;
    }

    public Message(String text, String from, String to, Long date) {
        this.text = text;
        this.userFromName = from;
        this.userToame = to;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserFromId() {
        return userFromId;
    }

    public Integer getUserToId() {
        return userToId;
    }

    public String getText() {
        return text;
    }

    public Long getDate() {
        return date;
    }

    public String getUserFromName() {
        return userFromName;
    }

    public String getUserToame() {
        return userToame;
    }
}
