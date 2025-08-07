package ca.algonquin.cst8288.web;

<<<<<<< HEAD
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
=======
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
>>>>>>> 7178af1 (Add initial NetBeans project files)
import java.io.IOException;

/**
 * Displays dashboard data.
<<<<<<< HEAD
 * @author Group1
 */

=======
 * @author Group20
 */
@WebServlet("/dashboard")
>>>>>>> 7178af1 (Add initial NetBeans project files)
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("dashboard.html").forward(request, response);
    }
}