package examples.model;


import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Department {
    @Id
    private String id;
    private String name;
    @OneToMany(mappedBy = "department")
    private Collection<Employee> employees;


    public Department() {
        employees = new ArrayList<Employee>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id.length() != 4) {
            throw new IllegalArgumentException("Department identifiers must be four characters in length");
        }
        this.id = id.toUpperCase();
    }

    public String getName() {
        return name;
    }

    public Collection<Employee> getEmployees() {
        return employees;
    }

    public String toString() {
        return "Department no: " + getId() + ", name: " + getName();
    }
}
