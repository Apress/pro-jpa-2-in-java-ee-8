package examples.model;

import java.util.Map;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;
import javax.persistence.Convert;

@Entity
public class Department {
    @Id
    private int id;
    private String name;
    
    @ManyToMany @MapKey(name="empName")
    @Convert(converter=UpperCaseConverter.class, attributeName="key.lastName")
    private Map<EmployeeName,Employee> employees;

    public Department() {
        employees = new HashMap<EmployeeName,Employee>();
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public Map<EmployeeName,Employee> getEmployees() {
        return employees;
    }

    public String toString() {
        String emps = "{ ";
        for (EmployeeName empName : getEmployees().keySet()) {
            emps += "(" + empName.toString() + ") ";
        }
        emps += "}";
        return "Department no: " + getId() + 
               ", name: " + getName() +
               ", employees: " + emps;
    }
}
