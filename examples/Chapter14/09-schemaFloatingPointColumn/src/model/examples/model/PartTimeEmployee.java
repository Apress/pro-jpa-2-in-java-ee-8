package examples.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PartTimeEmployee {
    @Id private int id;
    private String name;
    @Column(precision=8, scale=2) 
    private float hourlyRate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public float getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(float salary) {
        this.hourlyRate = salary;
    }
}
