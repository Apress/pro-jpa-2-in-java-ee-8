package examples.stateless;

import java.util.Collection;

import examples.model.Phone;

public interface PhoneService {
    public Phone createPhone(String num, String type);
    public Collection<Phone> findAllPhones();
}
