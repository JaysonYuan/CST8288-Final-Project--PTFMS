package ca.algonquin.cst8288.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject for maintenance notifications.
<<<<<<< HEAD
 * @author Group1
=======
 * @author Group20
>>>>>>> 7178af1 (Add initial NetBeans project files)
 */
public class VehicleSubject {
    private List<MaintenanceObserver> observers = new ArrayList<>();
    private String vehicleNumber;

    public VehicleSubject(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public void addObserver(MaintenanceObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (MaintenanceObserver observer : observers) {
            observer.update(vehicleNumber, message);
        }
    }
}