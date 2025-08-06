package ca.algonquin.cst8288.model;

/**
 * Builder for Vehicle objects.
 * @author Group1
 */
public class VehicleBuilder {
    private String vehicleNumber;
    private String type;
    private String fuelEnergyType;
    private double consumptionRate;
    private int maxPassengers;
    private int currentRouteId;

    public VehicleBuilder setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
        return this;
    }

    public VehicleBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public VehicleBuilder setFuelEnergyType(String fuelEnergyType) {
        this.fuelEnergyType = fuelEnergyType;
        return this;
    }

    public VehicleBuilder setConsumptionRate(double consumptionRate) {
        this.consumptionRate = consumptionRate;
        return this;
    }

    public VehicleBuilder setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
        return this;
    }

    public VehicleBuilder setCurrentRouteId(int currentRouteId) {
        this.currentRouteId = currentRouteId;
        return this;
    }

    public Vehicle build() {
        return new Vehicle(vehicleNumber, type, fuelEnergyType, consumptionRate, maxPassengers, currentRouteId);
    }
}