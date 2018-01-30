package examples.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Employee {
    @Id
    private int id;
    private String name;
    
    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name="PSPACE_ID") 
    ParkingSpace parkingSpace;

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},
               mappedBy="employee")
    Collection<Phone> phones;

    public Employee() {
        phones = new ArrayList<Phone>();
    }
    
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
