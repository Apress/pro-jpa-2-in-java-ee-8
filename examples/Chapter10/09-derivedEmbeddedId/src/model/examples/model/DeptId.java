package examples.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DeptId implements Serializable {
    @Column(name="NUM")
    private int number;
    
    @Column(name="CTRY")    
    private String country;
    
    public DeptId() {
    }

    public DeptId(int number, String country) {
        this.number = number;
        this.country = country;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String toString() {
        return "DeptId(" + getNumber() + "," + getCountry() + ")";
    }
}