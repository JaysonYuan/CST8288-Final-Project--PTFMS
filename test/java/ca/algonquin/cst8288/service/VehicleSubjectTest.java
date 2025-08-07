package ca.algonquin.cst8288.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for VehicleSubject observer pattern.
 */
public class VehicleSubjectTest {

    // 简单测试用 Observer 实现
    static class TestObserver implements MaintenanceObserver {
        String lastVehicleId;
        String lastMessage;

        @Override
        public void update(String vehicleNumber, String message) {
            lastVehicleId = vehicleNumber;
            lastMessage   = message;
        }
    }

    @Test
    void notifyObservers_singleObserver_receiveUpdate() {
        VehicleSubject subject = new VehicleSubject("V123");
        TestObserver observer  = new TestObserver();
        subject.addObserver(observer);

        subject.notifyObservers("Maintenance needed");

        assertEquals("V123", observer.lastVehicleId);
        assertEquals("Maintenance needed", observer.lastMessage);
    }

    @Test
    void notifyObservers_multipleObservers_receiveUpdate() {
        VehicleSubject subject = new VehicleSubject("V456");
        TestObserver obs1 = new TestObserver();
        TestObserver obs2 = new TestObserver();
        subject.addObserver(obs1);
        subject.addObserver(obs2);

        subject.notifyObservers("Check engine");

        assertEquals("V456", obs1.lastVehicleId);
        assertEquals("Check engine", obs1.lastMessage);
        assertEquals("V456", obs2.lastVehicleId);
        assertEquals("Check engine", obs2.lastMessage);
    }
}
