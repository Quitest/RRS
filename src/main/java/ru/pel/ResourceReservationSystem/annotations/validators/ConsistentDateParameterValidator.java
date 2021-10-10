package ru.pel.ResourceReservationSystem.annotations.validators;

import ru.pel.ResourceReservationSystem.annotations.ConsistentDateParameters;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConsistentDateParameterValidator implements ConstraintValidator<ConsistentDateParameters, Object[]> {
    @Override
    public boolean isValid(Object[] objects, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
