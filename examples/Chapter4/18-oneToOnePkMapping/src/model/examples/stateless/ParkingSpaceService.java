package examples.stateless;

import java.util.Collection;

import examples.model.Employee;
import examples.model.ParkingSpace;

public interface ParkingSpaceService {
    public ParkingSpace createParkingSpace(Employee emp, int lot, String location);
    public Collection<ParkingSpace> findAllParkingSpaces();
}
