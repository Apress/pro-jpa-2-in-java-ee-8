package examples.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceUnitUtil;

@Entity
public class Department {
    @Id
    private int id;
    private String name;
    @OneToMany(mappedBy="department") 
    @MapKey(name="name")
    private Map<EmployeeName,Employee> employees = new HashMap<EmployeeName,Employee>();

    public int getId() {
        return id;
    }
    
    public void setId(int deptNo) {
        this.id = deptNo;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String deptName) {
        this.name = deptName;
    }
    
    public Map<EmployeeName,Employee> getEmployees() {
        return employees;
    }

    public String toString() {
        return "Department no: " + getId() + 
               ", name: " + getName();
    }
    
    public String toLoadedString(PersistenceUnitUtil util) {
        return this.getClass().getSimpleName() + ": " + getId() + 
               ", name: " + (util.isLoaded(this, "name") ? getName() : "N/L") +
               ", employees: " + (util.isLoaded(this, "employees") 
               	       ? loadedEmployeesString(util, getEmployees()) 
               	       : "N/L");
    }
    
    public String loadedEmployeesString(PersistenceUnitUtil util, Map<EmployeeName,Employee> emps) {
        String result = "[";
        for (Employee e : emps.values()) {
            result += " " + e.toLoadedString(util);
        }
        result += " ]";
        return result;
    }
}
