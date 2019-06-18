package com.viaplay.serviceapi.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-18
 */
public class UUIDValidator implements ConstraintValidator<UUIDConstrain, String> {

    private String pattern;

    @Override
    public void initialize(UUIDConstrain constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null || value.isEmpty()){
            return false;
        }
        Matcher matcher = Pattern.compile(pattern).matcher(value);
        return matcher.matches();
    }
}
