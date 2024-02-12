package com.example.networkmanagementsystem.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = StatusTypeValidator.class)
public @interface StatusType {
   public String message() default "Invalid status: It should be either online or offline";
   Class<?>[] groups() default {};
   Class<? extends Payload>[] payload() default {};
}
