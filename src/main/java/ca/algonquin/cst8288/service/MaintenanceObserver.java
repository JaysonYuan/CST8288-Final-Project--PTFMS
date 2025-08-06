package ca.algonquin.cst8288.service;

/**
 * Observer interface for maintenance alerts.
 * @author Group1
 */
public interface MaintenanceObserver {
    void update(String vehicleNumber, String message);
}