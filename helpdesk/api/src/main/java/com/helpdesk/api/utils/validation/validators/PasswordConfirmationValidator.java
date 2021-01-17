package com.helpdesk.api.utils.validation.validators;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.helpdesk.api.dto.UserCreationDTO;
import com.helpdesk.api.utils.validation.constraints.PasswordConfirmationConstraint;


public class PasswordConfirmationValidator
        implements ConstraintValidator<PasswordConfirmationConstraint, UserCreationDTO> {

    @Override
    public boolean isValid(UserCreationDTO value, ConstraintValidatorContext context) {
        return (value == null) ||
                Objects.equals(value.getPassword(), value.getPasswordConfirmation());
    }

}
