package examples.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;


@Entity
public class Department {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;
    
    @OneToMany(mappedBy="department")
    @MapKeyColumn(name="CUB_ID")
    private Map<String, Employee> employeesByCubicle;
    
    public Department() {
        employeesByCubicle = new HashMap<String, Employee>();
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
    
    public Map<String, Employee> getEmployees() {
        return employeesByCubicle;
    }

    public void addEmployee(String cubeId, Employee employee) {
        employeesByCubicle.put(cubeId, employee);
        if (employee.getDepartment() != null) {
            employee.getDepartment().removeEmployee(employee);
        }
        employee.setDepartment(this);
    }

    public void removeEmployee(Employee employee) {
        Iterator iter = employeesByCubicle.entrySet().iterator();
        while (iter.hasNext()) {
            Employee current = ((Map.Entry<String,Employee>) iter.next()).getValue();
            if (current.getId() == employee.getId()) {
                iter.remove();
                current.setDepartment(null);
            }
        }
    }

    public String toString() {
        StringBuffer aBuffer = new StringBuffer("Department ");
        aBuffer.append(" id: ");
        aBuffer.append(id);
        aBuffer.append(" name: ");
        aBuffer.append(name);
        aBuffer.append(" employeeCount: ");
        aBuffer.append(employeesByCubicle.size());
        return aBuffer.toString();
    }
}