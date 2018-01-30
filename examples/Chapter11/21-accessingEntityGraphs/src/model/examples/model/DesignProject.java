package examples.model;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("DP")
public class DesignProject extends Project {
}
