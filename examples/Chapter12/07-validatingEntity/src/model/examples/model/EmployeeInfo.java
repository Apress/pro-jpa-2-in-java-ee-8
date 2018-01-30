package examples.model;


import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.Past;

@Embeddable
public class EmployeeInfo {
    @Past
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Embedded
    private PersonInfo spouse;
}