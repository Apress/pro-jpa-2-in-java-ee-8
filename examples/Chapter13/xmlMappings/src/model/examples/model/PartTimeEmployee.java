package examples.model;

public class PartTimeEmployee extends CompanyEmployee {
	
    private float hourlyRate;

    public float getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(float hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String toString() {
        return "PartTimeEmployee(id=" + getId() + ", name=" + getName() + 
                ", vacation=" + getVacation() + ", rate=" + getHourlyRate() + ")";
    }
}
