package examples.model;

import java.util.Date;
import java.net.URL;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Embedded;
import javax.persistence.Convert;

@Entity
public class Employee {
    @Id
    private int id;

    @Embedded     
    @Convert(converter=UpperCaseConverter.class, attributeName="lastName")
    private EmployeeName empName;

    @Convert(converter=BooleanToIntegerConverter.class)
    private boolean fullTime;

    @Embedded
    @Convert(converter=BooleanToIntegerConverter.class, attributeName="bonded")
    private SecurityInfo securityInfo;

    private URL homePage;

    public int getId() {
        return id;
    }
    
    public String toString() {
        return "Employee " + getId() + 
               ": name: " + empName +
               ", fullTime: " + fullTime +
               ", homePage: " + homePage.getClass().getSimpleName() + "(" + homePage.toString() + ")" +
               ", secInfo: " + securityInfo.toString();
    }
}
