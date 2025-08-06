package ca.algonquin.cst8288.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for DieselConsumptionStrategy:
 * fuel consumption = distance * rate
 */
public class DieselConsumptionStrategyTest {

    @Test
    void calculateConsumption_givenDistanceAndRate_returnsDistanceTimesRate() {
        ConsumptionStrategy strategy = new DieselConsumptionStrategy();
        double distance = 50.0;
        double rate     = 0.2;  // 0.2 liters per km
        double expected = 10.0; // 50 * 0.2
        assertEquals(expected, strategy.calculateConsumption(distance, rate), 0.0001);
    }

    @Test
    void calculateConsumption_zeroRate_returnsZero() {
        ConsumptionStrategy strategy = new DieselConsumptionStrategy();
        assertEquals(0.0, strategy.calculateConsumption(100.0, 0.0), 0.0001);
    }
}
