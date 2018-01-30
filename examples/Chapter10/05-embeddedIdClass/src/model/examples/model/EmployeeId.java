package examples.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmployeeId {
    private String country; 
    @Column(name="EMP_ID")
    private int id; 

    public EmployeeId() {}
    public EmployeeId(String country, int id) {
        this.country = country;
        this.id = id;
    }


    public String getCountry() { return country; }
    public int getId() { return id; }

    public boolean equals(Object o) { 
        return ((o instanceof EmployeeId) && 
                country.equals(((EmployeeId)o).getCountry()) &&
                id == ((EmployeeId) o).getId());

    }

    public int hashCode() { 
        return country.hashCode() + id; 
    }
}