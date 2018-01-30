package examples.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LogRecord {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private int empNo;
    private String action;
    
    public LogRecord() {}
    public LogRecord(int empNo, String action) {
        setEmpNo(empNo);
        setAction(action);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String name) {
        this.action = name;
    }

    public String toString() {
        return "LogRecord empNo: " + getEmpNo() + ", action: " + getAction();
    }
}
