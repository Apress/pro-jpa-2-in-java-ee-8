package examples.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyJoinColumn;

@Entity
public class Department {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;
    
    @ElementCollection
    @CollectionTable(name="EMP_SENIORITY")
    @MapKeyJoinColumn(name="EMP_ID")
    @Column(name="SENIORITY")
    private Map<Employee, Integer> seniorities;
    
    public Department() {
        seniorities = new HashMap<Employee, Integer>();
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String deptName) {
        this.name = deptName;
    }
    
    public Map<Employee, Integer> getEmployees() {
        return seniorities;
    }

    public Map<Employee, Integer> getEmployeeSeniorities() {
        return seniorities;
    }

    public void setEmployeeSeniority(Employee employee, int seniority) {
        seniorities.put(employee, seniority);
    }

    public void removeEmployee(Employee employee) {
        seniorities.remove(employee);
    }

    public String toString() {
        StringBuffer aBuffer = new StringBuffer("Department ");
        aBuffer.append(" id: ");
        aBuffer.append(id);
        aBuffer.append(" name: ");
        aBuffer.append(name);
        aBuffer.append(" seniorities: ");
        aBuffer.append(getEmployeeSeniorities().toString());
        return aBuffer.toString();
    }
}