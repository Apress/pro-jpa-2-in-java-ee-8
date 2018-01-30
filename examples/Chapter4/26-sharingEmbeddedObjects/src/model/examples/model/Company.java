package examples.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Company {
    @Id private int id;
    
    @Embedded
    private Address address;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Address getAddress() {
        return this.address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    public String toString() {
        return "Company id: " + getId() + " address: " + getAddress();

    }
    
}