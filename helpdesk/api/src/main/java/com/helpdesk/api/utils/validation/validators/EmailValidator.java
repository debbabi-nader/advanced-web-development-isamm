package com.helpdesk.api.utils.validation.validators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.helpdesk.api.utils.validation.constraints.EmailConstraint;


public class EmailValidator implements ConstraintValidator<EmailConstraint, String> {

    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return (value == null) ||
                Pattern.compile(PATTERN_EMAIL).matcher(value).matches();
    }

}
