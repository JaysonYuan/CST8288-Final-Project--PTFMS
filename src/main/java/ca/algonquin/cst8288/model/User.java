package ca.algonquin.cst8288.model;

/**
 * Represents a User entity in the PTFMS.
 * @author Jiaxiang Yuan
 */
public class User {
    private int userId;
    private String name;
    private String email;
    private String password;
    private String userType;

    /**
     * Constructor for User.
     * @param userId The unique identifier for the user
     * @param name The user's name
     * @param email The user's email
     * @param password The user's password
     * @param userType The user's type (Manager or Operator)
     */
    public User(int userId, String name, String email, String password, String userType) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    // Getters and Setters

    /**
     * Gets the user ID.
     * @return The user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     * @param userId The user ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the user's name.
     * @return The user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's name.
     * @param name The user's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the user's email.
     * @return The user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email.
     * @param email The user's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's password.
     * @return The user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     * @param password The user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user's type (Manager or Operator).
     * @return The user's type
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Sets the user's type (Manager or Operator).
     * @param userType The user's type
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * Checks if the user is a Manager.
     * @return true if the user is a Manager, false otherwise
     */
    public boolean isManager() {
        return "Manager".equals(userType);
    }

    /**
     * Returns a string representation of the User.
     * @return String containing user details
     */
    @Override
    public String toString() {
        return "User{" +
               "userId=" + userId +
               ", name='" + name + '\'' +
               ", email='" + email + '\'' +
               ", userType='" + userType + '\'' +
               '}';
    }
}
