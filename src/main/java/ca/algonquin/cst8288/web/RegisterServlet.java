package ca.algonquin.cst8288.web;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/ptfms_db?useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userType = request.getParameter("user_type"); 

        if (name == null || email == null || password == null || userType == null ||
            name.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty()) {
            response.sendRedirect("register.html?error=empty");
            return;
        }

        if (emailExists(email)) {
            response.sendRedirect("register.html?error=emailExists");
            return;
        }

        String sql = "INSERT INTO Users (name, email, password, user_type) VALUES (?, ?, ?, ?)";

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
            System.err.println("Registration failed: " + e.getMessage());
            response.sendRedirect("register.html?error=database");
        }
    }

    private boolean emailExists(String email) {
        String sql = "SELECT * FROM Users WHERE email = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("Email check failed: " + e.getMessage());
            return true;
        }
    }
}
