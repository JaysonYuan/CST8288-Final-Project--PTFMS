package ca.algonquin.cst8288.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for VehicleBuilder:
 * when all properties are set, build() should return a Vehicle
 * with matching field values.
 */
public class VehicleBuilderTest {

    @Test
    void buildWithAllProperties_ShouldReturnVehicleWithSameValues() {
        VehicleBuilder builder = new VehicleBuilder()
            .setVehicleNumber("V123")
            .setType("Bus")
            .setFuelEnergyType("Electric")
            .setConsumptionRate(1.5)
            .setMaxPassengers(40)
            .setCurrentRouteId(99);

        Vehicle vehicle = builder.build();

        assertEquals("V123",     vehicle.getVehicleNumber());
        assertEquals("Bus",      vehicle.getType());
        assertEquals("Electric", vehicle.getFuelEnergyType());
        assertEquals(1.5,        vehicle.getConsumptionRate(), 0.0001);
        assertEquals(40,         vehicle.getMaxPassengers());
        assertEquals(99,         vehicle.getCurrentRouteId());
    }
}
