package com.example.demo;

import com.example.demo.interfaces.MyCustomConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyCustomConstraintValidator implements ConstraintValidator<MyCustomConstraint, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(!(s.equals("臺北") || s.equals("台中"))){
//        if(true){
            System.out.println(s);
            return false;
        }
        return true;
    }
}
