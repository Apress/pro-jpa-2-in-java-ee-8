package examples.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class VacationEntry {
    @Temporal(TemporalType.DATE)
    private Calendar startDate;
    @Column(name="DAYS")
    private int daysTaken;

    public VacationEntry() {}

    public VacationEntry(Calendar aStartDate, int daysTakenInt) {
        startDate = aStartDate;
        daysTaken = daysTakenInt;
    }
    
    public Calendar getStartDate() {
        return startDate;
    }
    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }
    public int getDaysTaken() {
        return daysTaken;
    }
    public void setDaysTaken(int daysTaken) {
        this.daysTaken = daysTaken;
    }

    public String toString() {
        return "VacationEntry startDate: " + getStartDate().getTime() + 
               ", daysTaken: " + getDaysTaken();
    }
}