package ca.algonquin.cst8288.web;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Handles user registration for the Public Transit Fleet Management System.
 * 
 * This servlet receives form data from register.html, checks for existing emails,
 * inserts a new user into the database if valid, and redirects with appropriate messages.
 * 
 * Author: Jiaxiang Yuan
 */
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection configuration
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ptfms_db?useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";

    /**
     * Handles POST requests for user registration.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get form parameters
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userType = request.getParameter("user_type");

        // Validate required fields
        if (name == null || email == null || password == null || userType == null ||
            name.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty()) {
            response.sendRedirect("register.html?error=empty");
            return;
        }

        // Check if email already exists
        if (emailExists(email)) {
            response.sendRedirect("register.html?error=emailExists");
            return;
        }

        // SQL statement to insert new user
        String sql = "INSERT INTO Users (name, email, password, user_type) VALUES (?, ?, ?, ?)";

        // Attempt to insert user into database
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, userType);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                response.sendRedirect("login.html?msg=registered");
            } else {
                response.sendRedirect("register.html?error=failed");
            }

        } catch (SQLException e) {
            // Print full stack trace for debugging
            e.printStackTrace();
            System.err.println("SQL Error: " + e.getMessage());

            // Redirect with database error
            response.sendRedirect("register.html?error=database");
        }
    }

    /**
     * Checks if the email is already registered in the database.
     *
     * @param email The email to check
     * @return true if email exists, false otherwise
     */
    private boolean emailExists(String email) {
        String sql = "SELECT * FROM Users WHERE email = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // true if a record is found

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error checking email existence: " + e.getMessage());
            return true; // Assume true to avoid duplicates if DB error
        }
    }
}
