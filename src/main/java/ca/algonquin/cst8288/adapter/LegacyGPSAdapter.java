package ca.algonquin.cst8288.adapter;

/**
 * Adapter for legacy GPS system.
<<<<<<< HEAD
 * @author Group1
=======
 * @author Group20
>>>>>>> 7178af1 (Add initial NetBeans project files)
 */
public class LegacyGPSAdapter {
    private LegacyGPS legacyGPS;

    public LegacyGPSAdapter(LegacyGPS legacyGPS) {
        this.legacyGPS = legacyGPS;
    }

    public double[] getCoordinates() {
        String legacyData = legacyGPS.getLegacyPosition();
        // Parse legacy format (e.g., "lat:45.4215,lon:-75.6972") to double array [lat, lon]
        String[] parts = legacyData.split(",");
        double latitude = Double.parseDouble(parts[0].split(":")[1]);
        double longitude = Double.parseDouble(parts[1].split(":")[1]);
        return new double[]{latitude, longitude};
    }
}

/**
 * Simulated legacy GPS system.
 */
class LegacyGPS {
    public String getLegacyPosition() {
        return "lat:45.4215,lon:-75.6972"; // Example legacy format
    }
}