package ca.algonquin.cst8288.dao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for DAOFactory.
 */
public class DAOFactoryTest {

    @Test
    void getInstance_shouldReturnSingletonInstance() {
        DAOFactory first = DAOFactory.getInstance();
        DAOFactory second = DAOFactory.getInstance();
        assertNotNull(first, "getInstance() should not return null");
        assertSame(first, second, "getInstance() should always return the same singleton instance");
    }

    @Test
    void getUserDAO_shouldReturnNonNullUserDAO() {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        assertNotNull(userDAO, "getUserDAO() should not return null");
        assertTrue(userDAO instanceof UserDAO, "getUserDAO() should return a UserDAO instance");
    }

    @Test
    void getVehicleDAO_shouldReturnNonNullVehicleDAO() {
        DAOFactory factory = DAOFactory.getInstance();
        VehicleDAO vehicleDAO = factory.getVehicleDAO();
        assertNotNull(vehicleDAO, "getVehicleDAO() should not return null");
        assertTrue(vehicleDAO instanceof VehicleDAO, "getVehicleDAO() should return a VehicleDAO instance");
    }
}
