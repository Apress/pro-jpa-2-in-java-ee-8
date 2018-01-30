package examples.model;

public class EmployeeDetails {
    private String name;
    private long salary;
    private String deptName;
    
    public EmployeeDetails(String name, long salary, String deptName) {
        this.name = name;
        this.salary = salary;
        this.deptName = deptName;
    }
    
    public String toString() {
        return "EmployeeDetails name: " + name +
               ", salary: " + salary +
               ", deptName: " + deptName;
    }
}
