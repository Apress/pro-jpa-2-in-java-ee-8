package examples.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name="PTEmp")
@DiscriminatorValue("PTEmp")
public class PartTimeEmployee extends CompanyEmployee {
    @Column(name="HOURLY_RATE")
    private float hourlyRate;

    public float getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(float hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String toString() {
        return "PartTimeEmployee id: " + getId() + " name: " + getName();
    }
}
