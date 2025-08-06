package ca.algonquin.cst8288.service;

/**
 * Strategy for diesel fuel consumption.
 * @author Group1
 */
public class DieselConsumptionStrategy implements ConsumptionStrategy {
    @Override
    public double calculateConsumption(double distance, double rate) {
        return distance * rate; // Fuel consumed in liters
    }
}