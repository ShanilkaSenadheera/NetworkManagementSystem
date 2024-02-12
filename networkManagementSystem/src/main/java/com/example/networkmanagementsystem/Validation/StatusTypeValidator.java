package com.example.networkmanagementsystem.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class StatusTypeValidator implements ConstraintValidator<StatusType, String> {

    @Override
    public boolean isValid(String status, ConstraintValidatorContext constraintValidatorContext) {
        List<String> statusList = Arrays.asList("offline","online");
        return statusList.contains(status);
    }
}
