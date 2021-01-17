package com.helpdesk.api.utils.validation.validators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.helpdesk.api.utils.validation.constraints.PasswordConstraint;


public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {

    private static final String PATTERN_PASSWORD = "^.*(?=.{8,})((?=.*[!@#$%^&*()\\-_=+{};:,<.>]){1})(?=.*\\d)((?=.*[a-z]){1})((?=.*[A-Z]){1}).*$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return (value == null) ||
                Pattern.compile(PATTERN_PASSWORD).matcher(value).matches();
    }

}
