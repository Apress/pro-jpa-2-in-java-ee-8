package examples.model;

import java.io.Serializable;

public class EmployeeId implements Serializable {
    private String country; 
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