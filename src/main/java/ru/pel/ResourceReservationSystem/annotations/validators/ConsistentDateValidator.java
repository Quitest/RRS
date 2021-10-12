package ru.pel.ResourceReservationSystem.annotations.validators;

import ru.pel.ResourceReservationSystem.annotations.ConsistentDates;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class ConsistentDateValidator implements ConstraintValidator<ConsistentDates, Object> {
    private String fistDateTimeFieldName;
    private String secondDateTimeFieldName;

    private Object getFieldValue(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        var field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }

    @Override
    public void initialize(ConsistentDates constraintAnnotation) {
        fistDateTimeFieldName = constraintAnnotation.firstField();
        secondDateTimeFieldName = constraintAnnotation.secondField();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        try {
            Object firstField = getFieldValue(object, fistDateTimeFieldName);
            Object secondField = getFieldValue(object, secondDateTimeFieldName);

            if (firstField == null || secondField == null) {
                return true;
            }

            if (firstField instanceof LocalDateTime first &&
                    secondField instanceof LocalDateTime second) {
                return first.isBefore(second);
            }
        } catch (Exception e) {
            //FIXME надо логгировать
            e.printStackTrace();
        }
        return false;
    }
}
