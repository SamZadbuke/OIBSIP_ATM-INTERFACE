public class User {
    private String userId;
    private String userPin;

    public User(String userId, String userPin) {
        this.userId = userId;
        this.userPin = userPin;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPin() {
        return userPin;
    }
}
