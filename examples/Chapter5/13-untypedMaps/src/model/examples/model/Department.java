package examples.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

@Entity
public class Department {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;
    
    @OneToMany(targetEntity=Employee.class, mappedBy="department")
    @MapKey
    private Map employees;
    
    public Department() {
        employees = new HashMap();
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
    
    public void addEmployee(Employee employee) {
        employees.put(employee.getId(), employee);
        if (employee.getDepartment() != null) {
            employee.getDepartment().getEmployees().remove(employee.getId());
        }
        employee.setDepartment(this);
    }
    
    public Map getEmployees() {
        return employees;
    }

    public String toString() {
        StringBuffer aBuffer = new StringBuffer("Department ");
        aBuffer.append(" id: ");
        aBuffer.append(id);
        aBuffer.append(" name: ");
        aBuffer.append(name);
        aBuffer.append(" employeeCount: ");
        if(null != employees) {           
            aBuffer.append(employees.size());
        }
        return aBuffer.toString();
    }
}