package examples.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Employee {
    @Id
    private int id;
    private String name;
    
    @ManyToOne(cascade=CascadeType.PERSIST)
    Address address;

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

    public Address getAddress() {
        return address;
    }
    
    public void setAddress(Address address) {
        this.address = address; 
    }

    public String toString() {
        return "Employee id: " + getId() + " name: " + getName() +
               " with " + getAddress();
    }
}
