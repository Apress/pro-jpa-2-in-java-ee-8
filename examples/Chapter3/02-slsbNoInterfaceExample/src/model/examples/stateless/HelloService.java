package examples.stateless;

import javax.ejb.Stateless;

@Stateless
public class HelloService {
    public String sayHello(String name) {
        return "Hello, "  + name;
    }
}

