package ca.algonquin.cst8288.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for ElectricConsumptionStrategy:
 * energy consumption = distance * rate
 */
public class ElectricConsumptionStrategyTest {

    @Test
    void calculateConsumption_validInputs_returnsDistanceTimesRate() {
        ConsumptionStrategy strategy = new ElectricConsumptionStrategy();
        double distance = 10.0;
        double rate     = 2.5;   // 2.5 kWh per km
        double expected = 25.0;  // 10 * 2.5
        assertEquals(expected, strategy.calculateConsumption(distance, rate), 0.0001);
    }

    @Test
    void calculateConsumption_zeroDistance_returnsZero() {
        ConsumptionStrategy strategy = new ElectricConsumptionStrategy();
        assertEquals(0.0, strategy.calculateConsumption(0.0, 2.5), 0.0001);
    }
}
