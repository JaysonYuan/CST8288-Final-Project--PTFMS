package ca.algonquin.cst8288.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ca.algonquin.cst8288.dao.*;
import java.io.IOException;

/**
 * ControllerServlet manages communication between layers.
 * @author Group1
 */
public class ControllerServlet extends HttpServlet {
    private UserDAO userDAO;
    private VehicleDAO vehicleDAO;

    @Override
    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        userDAO = daoFactory.getUserDAO();
        vehicleDAO = daoFactory.getVehicleDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("dashboard".equals(action)) {
            request.getRequestDispatcher("/dashboard").forward(request, response);
        } else if ("vehicle".equals(action)) {
            request.getRequestDispatcher("/vehicle").forward(request, response);
        } else {
            response.sendRedirect("login.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}