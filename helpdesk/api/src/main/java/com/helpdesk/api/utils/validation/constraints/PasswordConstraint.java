package com.helpdesk.api.utils.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.helpdesk.api.utils.validation.validators.PasswordValidator;


@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {

    String message() default "Password must have an 8 characters length at least, containing lower and upper case letters, digits and special characters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
