package model;

/**
 * Объект, выдаваемый пользователю сервером в момент его авторизации (токен авторизации).
 */
public class Token {
    private float token;

    public Token(float token) {
        this.token = token;
    }

    public float getToken() {
        return token;
    }
}
