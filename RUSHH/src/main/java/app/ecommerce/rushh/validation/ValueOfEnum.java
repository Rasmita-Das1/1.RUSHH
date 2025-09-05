package app.ecommerce.rushh.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValueOfEnumValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValueOfEnum {
    
    Class<? extends Enum<?>> enumClass();

    String message() default "must be a valid enum value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}



