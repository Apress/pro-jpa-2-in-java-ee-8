package examples.model;

import javax.ejb.ApplicationException;

@ApplicationException
public class ChangeCollisionException extends RuntimeException {
    public ChangeCollisionException() { super(); }
}

