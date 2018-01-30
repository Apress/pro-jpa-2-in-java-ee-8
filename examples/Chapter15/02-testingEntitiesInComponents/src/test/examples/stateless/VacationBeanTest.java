package examples.stateless;

import java.sql.Time;

import junit.framework.TestCase;
import examples.model.Employee;
import examples.stateless.EmployeeServiceBean;
import examples.stateless.VacationBean;

public class VacationBeanTest extends TestCase {    
    public void testYearsOfService() throws Exception {
        VacationBean bean = new VacationBean();
        bean.empService = new EmployeeServiceBean() {
            public Employee findEmployee(int id) {
                Employee emp = new Employee();
                emp.setStartDate(new Time(System.currentTimeMillis() - 
                                          VacationBean.MILLIS_PER_YEAR * 5));
                return emp;
            }
        };
        int yearsOfService = bean.getYearsOfService(0);
        assertEquals(5, yearsOfService);
    }
}
