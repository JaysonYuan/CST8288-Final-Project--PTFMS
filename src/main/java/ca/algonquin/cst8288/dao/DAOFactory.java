package ca.algonquin.cst8288.dao;

/**
 * Simple Factory for DAO objects.
<<<<<<< HEAD
 * @author Group1
=======
 * @author Group20
>>>>>>> 7178af1 (Add initial NetBeans project files)
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