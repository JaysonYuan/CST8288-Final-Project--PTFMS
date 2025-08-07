package ca.algonquin.cst8288.service;

/**
 * Strategy for electric energy consumption.
<<<<<<< HEAD
 * @author Group1
=======
 * @author Group20
>>>>>>> 7178af1 (Add initial NetBeans project files)
 */
public class ElectricConsumptionStrategy implements ConsumptionStrategy {
    @Override
    public double calculateConsumption(double distance, double rate) {
        return distance * rate; // Energy consumed in kWh
    }
}