package ca.algonquin.cst8288.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ca.algonquin.cst8288.dao.VehicleDAO;
import ca.algonquin.cst8288.model.VehicleBuilder;
import ca.algonquin.cst8288.model.Vehicle;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Handles vehicle management operations.
 * @author Group1
 */
@WebServlet("/vehicle")
public class VehicleManagementServlet extends HttpServlet {
    private VehicleDAO vehicleDAO;

    @Override
    public void init() throws ServletException {
        vehicleDAO = new VehicleDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("addVehicle".equals(action)) {
            Vehicle vehicle = new VehicleBuilder()
                    .setVehicleNumber(request.getParameter("vehicle_number"))
                    .setType(request.getParameter("type"))
                    .setFuelEnergyType(request.getParameter("fuel_energy_type"))
                    .setConsumptionRate(Double.parseDouble(request.getParameter("consumption_rate")))
                    .setMaxPassengers(Integer.parseInt(request.getParameter("max_passengers")))
                    .setCurrentRouteId(Integer.parseInt(request.getParameter("current_route_id")))
                    .build();
            try {
                vehicleDAO.addVehicle(vehicle);
                response.sendRedirect("vehicle_management.html?success=vehicle_added");
            } catch (SQLException e) {
                response.sendRedirect("vehicle_management.html?error=add_failed");
            }
        }
    }
}