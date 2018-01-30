package examples.model;

public class ManagerStats {
    private String managerName;
    private long employeeCount;
    private double avgSalary;
    
    public ManagerStats(String managerName, Long employeeCount, Double avgSalary) {
        this.managerName = managerName;
        this.employeeCount = employeeCount;
        this.avgSalary = avgSalary;
    }

    public String getManagerName() {
        return managerName;
    }

    public long getEmployeeCount() {
        return employeeCount;
    }

    public double getAverageSalary() {
        return avgSalary;
    }
    
    public String toString() {
        return "Manager: " + getManagerName() + 
                " empCount: " + getEmployeeCount() + 
                " avgSalary: " + getAverageSalary();
    }
}
