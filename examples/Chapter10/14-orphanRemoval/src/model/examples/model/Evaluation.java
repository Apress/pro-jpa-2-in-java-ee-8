package examples.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Evaluation {
    @Id protected int id;
    String evalText;

    public Evaluation() {}

    public Evaluation(int id, String text) {
        this.id = id;
        this.evalText = text;
    }

    public boolean equals(Evaluation eval) {
        return (eval != null) && (this.id == eval.getId());
    }

    public int hashCode() {
        return id;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getText() {
        return evalText;
    }
    
    public void setText(String text) {
        this.evalText = text;
    }
    
    public String toString() {
        return getClass().getSimpleName() + 
                " id: " + getId() + 
                ", text: " + getText();
    }
}
