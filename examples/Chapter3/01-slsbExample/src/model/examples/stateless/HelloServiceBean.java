package examples.stateless;

import javax.ejb.Stateless;

@Stateless
public class HelloServiceBean implements HelloService {
    public String sayHello(String name) {
        return "Hello, "  + name;
    }
}

