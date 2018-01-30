package examples.stateless;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import examples.model.Employee;

@Stateless
public class VacationBean implements Vacation {
    public static final long MILLIS_PER_YEAR = 1000 * 60 * 60 * 24 * 365;
    @EJB
    EmployeeService empService;
    
    public int getYearsOfService(int empId) {
        Employee emp = empService.findEmployee(empId);
        long current = System.currentTimeMillis();
        long start = emp.getStartDate().getTime();
        return (int)((current - start) / MILLIS_PER_YEAR);
    }
}

