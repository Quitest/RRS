package ru.pel.ResourceReservationSystem.annotations.validators;

import ru.pel.ResourceReservationSystem.annotations.ConsistentDates;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.time.LocalDateTime;

//@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class ConsistentDateValidator implements ConstraintValidator<ConsistentDates, Object> {
    private String fistDateTimeFieldName;
    private String secondDateTimeFieldName;

    private Object getFieldValue(Object object, String fieldName) throws Exception {
        var field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }

    @Override
    public void initialize(ConsistentDates constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
        fistDateTimeFieldName = constraintAnnotation.firstField();
        secondDateTimeFieldName = constraintAnnotation.secondField();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        try {
            Object firstField = getFieldValue(object, fistDateTimeFieldName);
            Object secondField = getFieldValue(object, secondDateTimeFieldName);

            if (firstField == null || secondField == null) {
                System.err.println("Сообщение от аннотации @ConsistentDates: результат true");
                return true;
            }

            if (firstField instanceof LocalDateTime first &&
                    secondField instanceof LocalDateTime second) {
                System.err.println("Сообщение от аннотации @ConsistentDates: результат " +first.isBefore(second));
                return first.isBefore(second);
            }
        } catch (Exception e) {
            //FIXME надо логгировать
            e.printStackTrace();
        }
        System.err.println("Сообщение от аннотации @ConsistentDates: даты не соответствуют условиям");
        return false;
    }
}
