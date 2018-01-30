package examples.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
//import javax.validation.ConstraintPayload; // renamed as Payload 
import javax.validation.Payload;

/**
* Indicate that a number should be even.
* May be applied on fields or properties of type Integer.
*/
@Constraint(validatedBy={EvenNumberValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Even {
    String message() default "Number must be even";
    Class<?>[] groups() default {};
    //Class<? extends ConstraintPayload>[] payload() default {};
    Class<? extends Payload>[] payload() default {};
    boolean includeZero() default true;
}