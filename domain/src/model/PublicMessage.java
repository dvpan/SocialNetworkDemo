package model;


public class PublicMessage {
    Integer id;
    String text;
    Integer user_id;
    Long date;

    public PublicMessage(Integer user_id, String text, Long date) {
        this.text = text;
        this.user_id = user_id;
        this.date = date;
    }

    public PublicMessage(String text, Long date) {
        this.text = text;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getUserId() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
