package examples.model;

import java.io.Serializable;

public class DeptId implements Serializable {
    private int number;
    private String country;

    public DeptId() {}

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
}