package examples.model;

public class Phone {

    private int id;
    private String number;
    private String type;
    private Employee employee;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNumber() {
        return number;
    }
    
    public void setNumber(String phoneNo) {
        this.number = phoneNo;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String phoneType) {
        this.type = phoneType;
    }
    
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String toString() {
        return "Phone id: " + getId() + 
               ", no: " + getNumber() +
               ", type: " + getType();
    }
}
