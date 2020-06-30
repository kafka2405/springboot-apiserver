package com.backend.apiserver.annotation.validator;

import com.backend.apiserver.annotation.CustomAnnotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomAnnotationValidator implements ConstraintValidator<CustomAnnotation, String> {

    /**
     * Prefix
     */
    private static final String KAFKA_PREFIX = "Kafka-";

    /**
     *  Check whether value is valid
     * @param s
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || s.isEmpty()) return false;
        return s.startsWith(KAFKA_PREFIX);
    }
}
