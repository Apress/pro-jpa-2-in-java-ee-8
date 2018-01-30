package examples.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Department {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy="department")
    @OrderBy("name ASC")
    private List<Employee> employees;

    public Department() {
        employees = new ArrayList<Employee>();
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
        // Need to add it in the correct position to maintain the order
        int initialSize = employees.size();
        if (!employees.contains(employee)) {
            for (int i=0; i < initialSize; i++) {
                if (employees.get(i).getName().compareTo(employee.getName()) > 0) {
                    employees.add(i, employee);
                    break;
                }
            }
           if (employees.size() == initialSize) {
                employees.add(employee);
            }
            if (employee.getDepartment() != null) {
                employee.getDepartment().getEmployees().remove(employee);
            }
            employee.setDepartment(this);
        }
    }
    
    public List<Employee> getEmployees() {
        return employees;
    }

    public String toString() {
        return "Department id: " + getId() + 
               ", name: " + getName();
    }
}
