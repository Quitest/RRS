package ru.pel.ResourceReservationSystem.annotations;

import ru.pel.ResourceReservationSystem.annotations.validators.ConsistentDateParameterValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

//TODO реализовать согласно документу https://www.baeldung.com/javax-validation-method-constraints

/**
 * message – returns the default key for creating error messages, this enables us to use message interpolation
 * groups – allows us to specify validation groups for our constraints
 * payload – can be used by clients of the Bean Validation API to assign custom payload objects to a constraint
 */
//@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ConsistentDateParameterValidator.class)
public @interface ConsistentDateParameters {
    Class<?>[] groups() default {};

    String message() default "Дата конца должна быть после даты начала, и обе они должны быть в будущем";

    Class<? extends Payload>[] payloads() default {};
}
