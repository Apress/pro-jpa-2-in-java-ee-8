package examples.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {
    @Id
    @Column(name = "EMP_ID")
    private int id;
    private String name;
    @Column(name = "SAL")
    private long salary;
    @Column(name = "COMM")
    private String comments;

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

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String toString() {
        return "Employee id: " + getId() + " name: " + getName() +
        " salary: " + getSalary() + " comments: " + getComments();
    }
}
