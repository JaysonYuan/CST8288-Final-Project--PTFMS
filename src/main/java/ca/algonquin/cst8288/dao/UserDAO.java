package ca.algonquin.cst8288.dao;

import ca.algonquin.cst8288.model.User;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DAO for User operations.
<<<<<<< HEAD
 * @author Group1
=======
 * @author Group20
>>>>>>> 7178af1 (Add initial NetBeans project files)
 */
public class UserDAO {
    private Connection getConnection() throws SQLException {
        Properties props = new Properties();
        try {
            props.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DriverManager.getConnection(
                props.getProperty("jdbc.url"),
                props.getProperty("jdbc.username"),
                props.getProperty("jdbc.password"));
    }

    public void registerUser(User user) throws SQLException {
        String sql = "INSERT INTO Users (name, email, password, user_type) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getUserType());
            stmt.executeUpdate();
        }
    }

    public User authenticateUser(String email, String password) throws SQLException {
        String sql = "SELECT * FROM Users WHERE email = ? AND password = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("user_id"), rs.getString("name"), rs.getString("email"),
                        rs.getString("password"), rs.getString("user_type"));
            }
            return null;
        }
    }
}