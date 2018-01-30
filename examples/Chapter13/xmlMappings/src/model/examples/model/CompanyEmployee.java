package examples.model;

public abstract class CompanyEmployee extends Employee {
	
    private int vacation;

    public int getVacation() {
        return vacation;
    }

    public void setVacation(int vacation) {
        this.vacation = vacation;
    }
}
