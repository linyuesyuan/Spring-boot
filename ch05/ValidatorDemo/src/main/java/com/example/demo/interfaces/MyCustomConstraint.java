package com.example.demo.interfaces;

import com.example.demo.MyCustomConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyCustomConstraintValidator.class)
public @interface MyCustomConstraint {
    String message() default "請輸入台灣城市縣市名稱";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
