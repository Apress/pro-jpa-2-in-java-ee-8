package examples.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Employee {
    @Id
    private int id;
    private String name;
    
    @OneToOne 
    @JoinColumn(name="PSPACE_ID") 
    private ParkingSpace parkingSpace;

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }
    
    public void setParkingSpace(ParkingSpace parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    public String toString() {
        return "Employee id: " + getId() + " name: " + getName() + 
               " with " + getParkingSpace();
    }
}
