package examples.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedSubgraph;
import javax.persistence.PersistenceUnitUtil;


@Entity
@DiscriminatorValue("CE")
public class ContractEmployee extends Employee {
    @Column(name="H_RATE")
    private int hourlyRate;

    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
    
    public String toString() {
        return super.toString() + 
               "[ hourlyRate: " + getHourlyRate() +
               " name: " + getName() +
               ", salary: " + getSalary() +
               ", phones: " + getPhones() +
               ", managerNo: " + ((getManager() == null) ? null : getManager().getId()) +
               ", deptNo: " + ((getDepartment() == null) ? null : getDepartment().getId()) +
               ", projects: " + getProjects() + "]";
    }
    public String toLoadedString(PersistenceUnitUtil util) {
        return "ContractEmployee " + getId() + 
               ": name: " + (util.isLoaded(this, "name") ? getName() : "N/L") +
               ", salary: " + (util.isLoaded(this, "salary") ? getSalary() : "N/L") +
               ", phones: " + (util.isLoaded(this, "phones") ? "[Phones]" : "N/L") +
               ", manager: " + (util.isLoaded(this, "manager") 
               	       ? ((getManager() == null) ? null : "["+getManager().toLoadedString(util)+"]") 
               	       : "N/L") +
               ", dept: " + (util.isLoaded(this, "department") 
               	       ? ((getDepartment() == null) ? null : getDepartment().getId()) 
               	       : "N/L") +
               ", projects: " + (util.isLoaded(this, "projects") 
               	       ? loadedProjectsString(util, getProjects()) 
               	       : "N/L");
    }
    
    public String loadedProjectsString(PersistenceUnitUtil util, Collection<Project> projs) {
        String result = "[";
        for (Project p : projs) {
            result += " " + p.getClass().getSimpleName() + " id=" + p.getId();
            result += " name: " + (util.isLoaded(p, "name") ? p.getName() : "N/L");
            if (p instanceof QualityProject) {
            	QualityProject qp = (QualityProject)p;
                result += " qaRating: " + (util.isLoaded(qp, "qaRating") ? qp.getQaRating() : "N/L");
            }
        }
        result += " ]";
        return result;
    }
}
