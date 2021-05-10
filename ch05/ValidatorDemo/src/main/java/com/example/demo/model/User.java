package com.example.demo.model;

import com.example.demo.interfaces.MyCustomConstraint;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
public class User {
    private Long id;

    @NotBlank
    @Length(min = 5, max = 20, message = "使用者名稱長度為5-20個字元")
    private String name;

    @NotNull
    @Min(value = 18, message = "最小18歲")
    @Max(value = 60, message = "最大60歲")
    private Integer age;

    @Email(message = "請輸入電子郵件")
    @NotBlank(message = "電子郵件不能為空")
    private String email;
    
    @MyCustomConstraint
    private String answer;

}
