package ca.algonquin.cst8288.adapter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for LegacyGPSAdapter:
 * parses "lat:45.4215,lon:-75.6972" into [45.4215, -75.6972]
 */
public class LegacyGPSAdapterTest {

    @Test
    void getCoordinates_validLegacyData_parsedCorrectly() {
        // Arrange: a LegacyGPS instance that returns example legacy data
        LegacyGPS legacy = new LegacyGPS();
        LegacyGPSAdapter adapter = new LegacyGPSAdapter(legacy);

        // Act: parse the coordinates
        double[] coords = adapter.getCoordinates();

        // Assert: array is not null and has length 2
        assertNotNull(coords, "Coordinates array should not be null");
        assertEquals(2, coords.length, "Coordinates array length must be 2");

        // Assert: latitude and longitude parsed correctly
        assertEquals(45.4215, coords[0], 0.000001, "Latitude should be 45.4215");
        assertEquals(-75.6972, coords[1], 0.000001, "Longitude should be -75.6972");
    }
}
