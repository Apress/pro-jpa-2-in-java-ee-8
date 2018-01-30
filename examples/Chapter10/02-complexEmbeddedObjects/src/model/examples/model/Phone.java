package examples.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Phone {
    @Id
    String num;
    
    @ManyToMany(mappedBy = "contactInfo.phones")
    List<Employee> employees;
    
    String type;
    
    public String getNum() {
        return num;
    }
    public void setNum(String num) {
        this.num = num;
    }
    public List<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return "Phone num: " + num + " type: " + type;
    }
    
}