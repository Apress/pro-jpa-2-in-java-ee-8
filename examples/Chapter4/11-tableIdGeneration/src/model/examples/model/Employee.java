package examples.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

@Entity
public class Employee {
    @TableGenerator(name="Emp_Gen", 
            table="ID_GEN",
            pkColumnName="GEN_NAME",
            valueColumnName="GEN_VAL")
    @Id @GeneratedValue(strategy=GenerationType.TABLE,
                        generator="Emp_Gen")
    private int id;
    private String name;
    private long salary;
    
    @OneToOne
    private Address address;

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

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }
    
    public Address getAddress() {
        return address;
    }
    
    public void setAddress(Address address) {
        this.address = address; 
    }
    
    public String toString() {
        return "Employee id: " + getId() + " name: " + getName() + " salary: " + getSalary();
    }
}
