package ca.algonquin.cst8288.model;

/**
 * Represents a Vehicle entity.
 * @author Group1
 */
public class Vehicle {
    private String vehicleNumber;
    private String type;
    private String fuelEnergyType;
    private double consumptionRate;
    private int maxPassengers;
    private int currentRouteId;

    public Vehicle(String vehicleNumber, String type, String fuelEnergyType, double consumptionRate,
                   int maxPassengers, int currentRouteId) {
        this.vehicleNumber = vehicleNumber;
        this.type = type;
        this.fuelEnergyType = fuelEnergyType;
        this.consumptionRate = consumptionRate;
        this.maxPassengers = maxPassengers;
        this.currentRouteId = currentRouteId;
    }

    // Getters and Setters
    public String getVehicleNumber() { return vehicleNumber; }
    public String getType() { return type; }
    public String getFuelEnergyType() { return fuelEnergyType; }
    public double getConsumptionRate() { return consumptionRate; }
    public int getMaxPassengers() { return maxPassengers; }
    public int getCurrentRouteId() { return currentRouteId; }
}