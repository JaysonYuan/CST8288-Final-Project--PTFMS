package ca.algonquin.cst8288.dao;

import ca.algonquin.cst8288.model.Vehicle;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DAO for Vehicle operations, handling CRUD operations for vehicles in the PTFMS.
<<<<<<< HEAD
 * @author Group1
=======
 * @author Group20
>>>>>>> 7178af1 (Add initial NetBeans project files)
 */
public class VehicleDAO {
    /**
     * Establishes a database connection using properties from db.properties.
     * @return Connection object
     * @throws SQLException if a database access error occurs
     */
    private Connection getConnection() throws SQLException {
        Properties props = new Properties();
        try {
            props.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException ex) {
            Logger.getLogger(VehicleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DriverManager.getConnection(
                props.getProperty("jdbc.url"),
                props.getProperty("jdbc.username"),
                props.getProperty("jdbc.password"));
    }

    /**
     * Adds a new vehicle to the database.
     * @param vehicle The vehicle to add
     * @throws SQLException if a database access error occurs
     */
    public void addVehicle(Vehicle vehicle) throws SQLException {
        String sql = "INSERT INTO Vehicles (vehicle_number, type, fuel_energy_type, consumption_rate, max_passengers, current_route_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vehicle.getVehicleNumber());
            stmt.setString(2, vehicle.getType());
            stmt.setString(3, vehicle.getFuelEnergyType());
            stmt.setDouble(4, vehicle.getConsumptionRate());
            stmt.setInt(5, vehicle.getMaxPassengers());
            stmt.setInt(6, vehicle.getCurrentRouteId());
            stmt.executeUpdate();
        }
    }

    /**
     * Retrieves a vehicle by its vehicle number.
     * @param vehicleNumber The unique vehicle number
     * @return Vehicle object or null if not found
     * @throws SQLException if a database access error occurs
     */
    public Vehicle getVehicleByNumber(String vehicleNumber) throws SQLException {
        String sql = "SELECT * FROM Vehicles WHERE vehicle_number = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vehicleNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Vehicle(
                        rs.getString("vehicle_number"),
                        rs.getString("type"),
                        rs.getString("fuel_energy_type"),
                        rs.getDouble("consumption_rate"),
                        rs.getInt("max_passengers"),
                        rs.getInt("current_route_id")
                );
            }
            return null;
        }
    }

    /**
     * Retrieves all vehicles from the database.
     * @return List of all vehicles
     * @throws SQLException if a database access error occurs
     */
    public List<Vehicle> getAllVehicles() throws SQLException {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM Vehicles";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vehicles.add(new Vehicle(
                        rs.getString("vehicle_number"),
                        rs.getString("type"),
                        rs.getString("fuel_energy_type"),
                        rs.getDouble("consumption_rate"),
                        rs.getInt("max_passengers"),
                        rs.getInt("current_route_id")
                ));
            }
            return vehicles;
        }
    }

    /**
     * Logs GPS data (arrival/departure times) for a vehicle at a station.
     * @param vehicleId The vehicle ID
     * @param stationId The station ID
     * @param arrivalTime The arrival time
     * @param departureTime The departure time
     * @throws SQLException if a database access error occurs
     */
    public void logGPSTracking(int vehicleId, int stationId, Timestamp arrivalTime, Timestamp departureTime) throws SQLException {
        String sql = "INSERT INTO Trip_Logs (vehicle_id, station_id, arrival_time, departure_time) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            stmt.setInt(2, stationId);
            stmt.setTimestamp(3, arrivalTime);
            stmt.setTimestamp(4, departureTime);
            stmt.executeUpdate();
        }
    }

    /**
     * Logs fuel/energy consumption for a vehicle.
     * @param vehicleId The vehicle ID
     * @param logTime The time of the log
     * @param amountConsumed The amount of fuel/energy consumed
     * @param distanceTraveled The distance traveled
     * @throws SQLException if a database access error occurs
     */
    public void logConsumption(int vehicleId, Timestamp logTime, double amountConsumed, double distanceTraveled) throws SQLException {
        String sql = "INSERT INTO Consumption_Logs (vehicle_id, log_time, amount_consumed, distance_traveled) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            stmt.setTimestamp(2, logTime);
            stmt.setDouble(3, amountConsumed);
            stmt.setDouble(4, distanceTraveled);
            stmt.executeUpdate();
        }
    }

    /**
     * Logs vehicle component usage for predictive maintenance.
     * @param vehicleId The vehicle ID
     * @param componentType The type of component
     * @param hoursUsed Hours used since last maintenance
     * @param maintenanceThreshold Threshold for maintenance
     * @throws SQLException if a database access error occurs
     */
    public void logComponentUsage(int vehicleId, String componentType, double hoursUsed, double maintenanceThreshold) throws SQLException {
        String sql = "INSERT INTO Vehicle_Components (vehicle_id, component_type, hours_used, maintenance_threshold) " +
                     "VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            stmt.setString(2, componentType);
            stmt.setDouble(3, hoursUsed);
            stmt.setDouble(4, maintenanceThreshold);
            stmt.executeUpdate();
        }
    }

    /**
     * Schedules a maintenance task for a vehicle.
     * @param vehicleId The vehicle ID
     * @param componentId The component ID (nullable)
     * @param description The maintenance task description
     * @param scheduledDate The scheduled date
     * @param cost The estimated cost
     * @throws SQLException if a database access error occurs
     */
    public void scheduleMaintenance(int vehicleId, Integer componentId, String description, Date scheduledDate, double cost) throws SQLException {
        String sql = "INSERT INTO Maintenance_Tasks (vehicle_id, component_id, description, scheduled_date, cost) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vehicleId);
            if (componentId != null) {
                stmt.setInt(2, componentId);
            } else {
                stmt.setNull(2, Types.INTEGER);
            }
            stmt.setString(3, description);
            stmt.setDate(4, scheduledDate);
            stmt.setDouble(5, cost);
            stmt.executeUpdate();
        }
    }
}