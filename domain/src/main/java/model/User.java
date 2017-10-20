package model;

/**
 * Класс, представляющий собой пользователя.
 */
public class User {
    private Integer id;
    private String name;
    private String login;
    private String description;
    private Integer hashPass;

    public User(Integer id, String name, String login, String description) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.description = description;
    }

    public User(String name, String login) {
        this.name = name;
        this.login = login;
    }

    public User(String name, String login, Integer hashPass) {
        this.name = name;
        this.login = login;
        this.hashPass = hashPass;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPassword() {
        return hashPass;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }
}
