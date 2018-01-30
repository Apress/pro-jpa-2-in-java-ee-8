package examples.model;

public class ParkingSpace {
	
    private int id;
    private int lot;
    private String location;
    private Employee employee;

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public int getLot() {
        return lot;
    }

    public void setLot(int lot) {
        this.lot = lot;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String deptName) {
        this.location = deptName;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public String toString() {
        return "ParkingSpace(id=" + getId() + ", lot=" + getLot() +
               ", location=" + getLocation() + ")";
    }
}
