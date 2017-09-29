package model;

import java.util.Date;

/**
 * Класс, представляющий собой сообщения, которые пользователи могут отправлять друг другу.
 */
public class Message {
    private Integer id;
    private Integer userFromId;
    private Integer userToId;

    private String text;
    private Long date;

    public Message(Integer id, Integer userFromId, Integer userToId, String text, Long date) {
        this.id = id;
        this.userFromId = userFromId;
        this.userToId = userToId;
        this.text = text;
        this.date = date;
    }

    public Message(Integer userFromId, Integer userToId, String text) {
        this.userFromId = userFromId;
        this.userToId = userToId;
        this.text = text;
        this.date = new Date().getTime();
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
}
