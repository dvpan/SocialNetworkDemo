package model;

/**
 * Класс, представляющий собой пользователя.
 */
public class User {
    private Integer id;
    private String coverUrl;
    private String firstName;
    private String secondName;
    private String email;
    private String description;

    public User(Integer id, String coverUrl, String firstName, String secondName, String email, String description) {
        this.id = id;
        this.coverUrl = coverUrl;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.description = description;
    }

    public User(String coverUrl, String firstName, String secondName, String email, String description) {
        this.coverUrl = coverUrl;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }
}
