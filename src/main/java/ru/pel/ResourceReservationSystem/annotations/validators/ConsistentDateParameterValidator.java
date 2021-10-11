package ru.pel.ResourceReservationSystem.annotations.validators;

import ru.pel.ResourceReservationSystem.annotations.ConsistentDateParameters;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.time.LocalDateTime;

@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class ConsistentDateParameterValidator implements ConstraintValidator<ConsistentDateParameters, Object[]> {
    @Override
    public boolean isValid(Object[] objects, ConstraintValidatorContext constraintValidatorContext) {
        if (objects[0] == null || objects[1] == null) {
            return true;
        }

        if (!(objects[0] instanceof LocalDateTime) || !(objects[1] instanceof LocalDateTime)) {
            throw new IllegalArgumentException("Не правильные входные данные - ожидалось два аргумента типа LocalDateTime");
        }

        return ((LocalDateTime) objects[0]).isAfter(LocalDateTime.now()) &&
                ((LocalDateTime) objects[0]).isBefore(((LocalDateTime) objects[1]));
    }
}
