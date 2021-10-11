package ru.pel.ResourceReservationSystem.annotations;

import ru.pel.ResourceReservationSystem.annotations.validators.ConsistentDateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//TODO реализовать согласно документу https://www.baeldung.com/javax-validation-method-constraints
// http://dolszewski.com/java/multiple-field-validation/

/**
 *
 *
 * message – returns the default key for creating error messages, this enables us to use message interpolation
 * groups – allows us to specify validation groups for our constraints
 * payload – can be used by clients of the Bean Validation API to assign custom payload objects to a constraint
 */
//@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ConsistentDateValidator.class)
public @interface ConsistentDates {
    Class<?>[] groups() default {};

    String message() default "Дата конца должна быть после даты начала, и обе они должны быть в будущем";

    Class<? extends Payload>[] payload() default {};

    String firstField();
    String secondField();
//    String dateFormat() default "yyyy-MM-dd'T'hh:mm:ss";
}
