package examples.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.persistence.OneToMany;

@Entity
public class Employee {
    @Id
    private int id;
    @Version private int version;
    private String name;
    private float cost;
    @OneToMany(mappedBy="employee")
    private Collection<Uniform> uniforms;

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

    public float getCost() {
        return cost;
    }

    public void setCost(float salary) {
        this.cost = salary;
    }
    
    public Collection<Uniform> getUniforms() {
        return uniforms;
    }
    
    public void addUniform(Uniform uniform) {
        getUniforms().add(uniform);
    }
    
    public void setUniforms(Collection<Uniform> uniforms) {
        this.uniforms = uniforms;
    }

    public String toString() {
        return "Employee id: " + getId() + " name: " + getName();
    }
}
