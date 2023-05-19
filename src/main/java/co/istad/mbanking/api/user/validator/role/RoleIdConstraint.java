package co.istad.mbanking.api.user.validator.role;


import co.istad.mbanking.api.user.validator.role.RoleIdConstraintValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RoleIdConstraintValidation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface RoleIdConstraint {
    String message() default "role id not exist ";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
