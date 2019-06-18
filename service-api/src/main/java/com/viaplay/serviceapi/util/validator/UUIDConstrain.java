package com.viaplay.serviceapi.util.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-18
 */
@Constraint(validatedBy = UUIDValidator.class)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface UUIDConstrain {
    String message() default "Invalid UUID format.";
    String pattern() default "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
