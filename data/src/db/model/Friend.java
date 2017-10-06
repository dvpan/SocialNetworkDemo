package db.model;

/**
 * Created by danpa on 06.10.2017.
 */
public class Friend {
    Integer id;
    Integer userId;
    Integer friendId;

    public Friend(Integer userId, Integer friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public boolean equals(Friend friend) {
        return friend.getUserId().equals(userId)&&friend.getFriendId().equals(friendId);
    }
}
