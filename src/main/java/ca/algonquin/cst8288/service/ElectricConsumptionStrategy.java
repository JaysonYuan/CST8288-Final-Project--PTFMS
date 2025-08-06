package ca.algonquin.cst8288.service;

/**
 * Strategy for electric energy consumption.
 * @author Group1
 */
public class ElectricConsumptionStrategy implements ConsumptionStrategy {
    @Override
    public double calculateConsumption(double distance, double rate) {
        return distance * rate; // Energy consumed in kWh
    }
}