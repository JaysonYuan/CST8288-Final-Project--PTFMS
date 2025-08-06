package ca.algonquin.cst8288.service;

/**
 * Strategy interface for fuel/energy consumption calculation.
 * @author Group20
 */
public interface ConsumptionStrategy {
    double calculateConsumption(double distance, double rate);
}