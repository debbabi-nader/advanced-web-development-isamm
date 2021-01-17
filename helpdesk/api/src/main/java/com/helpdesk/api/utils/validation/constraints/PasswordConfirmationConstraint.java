package com.helpdesk.api.utils.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.helpdesk.api.utils.validation.validators.PasswordConfirmationValidator;


@Documented
@Constraint(validatedBy = PasswordConfirmationValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConfirmationConstraint {

    String message() default "Password is not confirmed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
