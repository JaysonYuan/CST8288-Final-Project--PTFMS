package ca.algonquin.cst8288.web;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet handling user login for the CST8288 Final Project.
 * Redirects to different dashboards upon successful login based on user_type.
 * Redirects back to login.html with error message on failure.
 * 
 * @author Group20
 */
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/ptfms_db?useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL Driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL Driver not found: " + e.getMessage());
            throw new ServletException("Database driver not found", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || password == null || email.trim().isEmpty() || password.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Email and password are required.");
            request.getRequestDispatcher("/login.html").forward(request, response);
            return;
        }

        String userType = getUserType(email, password);
        if (userType != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userEmail", email);
            session.setAttribute("userType", userType);

            // Redirect based on user_type
            if (userType.equalsIgnoreCase("manager")) {
                response.sendRedirect("managerDashboard.html");
            } else if (userType.equalsIgnoreCase("operator")) {
                response.sendRedirect("operatorDashboard.html");
            } else {
                response.sendRedirect("login.html?error=invalidRole");
            }
        } else {
            request.setAttribute("errorMessage", "Invalid email or password.");
            request.getRequestDispatcher("/login.html").forward(request, response);
        }
    }

    private String getUserType(String email, String password) {
        String sql = "SELECT user_type FROM Users WHERE email = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("user_type");
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving user_type: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void destroy() {
        // Optional cleanup
    }
}
