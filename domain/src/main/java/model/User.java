package model;

/**
 * Класс, представляющий собой пользователя.
 */
public class User {
    private Integer id;
    private String name;
    private String login;
    private Integer hashPass;

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

    public Integer getPassword() {
        return hashPass;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }
}
