package com.backend.apiserver.annotation;

import com.backend.apiserver.annotation.validator.CustomAnnotationValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = CustomAnnotationValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomAnnotation {

    /**
     * Validation message
     */
    String message() default "Message throw when field is not have prefix kafka-";

    /**
     * Hibernate Validator required to operating
     */
    Class<?>[] groups() default {};

    /**
     * Hibernate Validator required to operating
     */
    Class<? extends Payload>[] payload() default {};
}
