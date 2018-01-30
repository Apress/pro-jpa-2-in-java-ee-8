package examples.model;

import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Employee {
    @Id
    private int id;
    private String name;
    private long salary;
    @Transient 
    private String convertedName;

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
        convertedName = convertName(name);
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public String getConvertedName() {
        return convertedName;
    }

    public String toString() {
        return "Employee " + " id: " + getId() + " name: " + getName() + " converted name: " + getConvertedName() + " salary: " + getSalary();
    }

    protected String convertName(String name) {
        // Convert to upper case Canadian...
        return name.toUpperCase(Locale.CANADA);
    }
}
