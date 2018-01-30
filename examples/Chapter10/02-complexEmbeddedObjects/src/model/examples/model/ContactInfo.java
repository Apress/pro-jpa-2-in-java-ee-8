package examples.model;

import java.util.Map;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;

@Embeddable
@Access(AccessType.FIELD)
public class ContactInfo {
    @Embedded
    private Address residence;
    
    @ManyToOne
    @JoinColumn(name = "PRI_NUM")
    private Phone primaryPhone;
    
    @ManyToMany
    @MapKey(name = "type")
    @JoinTable(name = "EMP_PHONES")
    private Map<String, Phone> phones;
    
    public Address getResidence() {
        return residence;
    }
    public void setResidence(Address residence) {
        this.residence = residence;
    }
    public Phone getPrimaryPhone() {
        return primaryPhone;
    }
    public void setPrimaryPhone(Phone primaryPhone) {
        this.primaryPhone = primaryPhone;
    }
    public Map<String, Phone> getPhones() {
        return phones;
    }
    public void setPhones(Map<String, Phone> phones) {
        this.phones = phones;
    }

    public String toString() {
        return "ContactInfo residence: " + getResidence() + " primaryPhone: " + getPrimaryPhone() +
               " phones " + getPhones();
    }

}
