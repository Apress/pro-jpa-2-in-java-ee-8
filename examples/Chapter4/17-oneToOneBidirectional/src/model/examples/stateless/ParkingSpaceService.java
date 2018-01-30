package examples.stateless;

import java.util.Collection;

import examples.model.ParkingSpace;

public interface ParkingSpaceService {
    public ParkingSpace createParkingSpace(int lot, String location);
    public Collection<ParkingSpace> findAllParkingSpaces();
}
