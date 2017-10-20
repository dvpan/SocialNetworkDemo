package model;

import java.util.Date;

/**
 * Класс, представляющий собой графические изображения.
 */
public class Image {
    private Integer id;
    private Integer userOwnerId;
    private String imageBase64;
    private String description;
    private Long date;

    public Image(Integer userOwnerId, String imageBase64, String description) {
        this.userOwnerId = userOwnerId;
        this.imageBase64 = imageBase64;
        this.description = description;
        this.date = new Date().getTime();
    }

    public Image(Integer id, Integer userOwnerId, String imageBase64, String description, Long date) {
        this.id = id;
        this.userOwnerId = userOwnerId;
        this.imageBase64 = imageBase64;
        this.description = description;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserOwnerId() {
        return userOwnerId;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public String getDescription() {
        return description;
    }

    public Long getDate() {
        return date;
    }
}
