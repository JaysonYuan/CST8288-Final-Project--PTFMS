package ca.algonquin.cst8288.dao;

/**
 * Simple Factory for DAO objects.
 * @author Zhongkai Li
 */
public class DAOFactory {
    private static DAOFactory instance = new DAOFactory();

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return new UserDAO();
    }

    public VehicleDAO getVehicleDAO() {
        return new VehicleDAO();
    }
}
