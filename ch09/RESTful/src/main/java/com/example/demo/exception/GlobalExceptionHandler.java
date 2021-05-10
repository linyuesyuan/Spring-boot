package com.example.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    // 400 - Bad Request
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Map<String, Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException e){
        logger.error("缺少請求參數", e);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 400);
        map.put("message", e.getMessage());
        return map;
    }
    // 400 - Bad Request
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Map<String, Object> handlerHttpMessageNotReadableException(HttpMessageNotReadableException e){
        logger.error("缺少請求參數", e);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 400);
        map.put("message", e.getMessage());
        return map;
    }
    // 400 - Bad Request
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e){
        logger.error("缺少請求參數", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", code);
        map.put("message", message);
        return map;
    }
    // 400 - Bad Request
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public Map<String, Object> handlerBindException(BindException e){
        logger.error("缺少請求參數", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", code);
        map.put("message", message);
        return map;
    }
    // 400 - Bad Request
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, Object> handleServiceException(ConstraintViolationException e){
        logger.error("缺少請求參數", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String message = violation.getMessage();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 400);
        map.put("message", message);
        return map;
    }
    // 400 - Bad Request
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public Map<String, Object> handleValidationException(ValidationException e){
        logger.error("參數驗證失敗", e);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 400);
        map.put("message", e.getMessage());
        return map;
    }
    //405 - Method Not Allowed
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Map<String, Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        logger.error("不支援目前請求方法", e);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 400);
        map.put("message", e.getMessage());
        return map;
    }
    // 415 - Unsupported Media Type
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Map<String, Object> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e){
        logger.error("不支援目前媒體類型", e);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 415);
        map.put("message", e.getMessage());
        return map;
    }

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public Map<String, Object> businessExceptionHandler(BusinessException e){
        logger.error("自訂業務失敗", e);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", e.getCode());
        map.put("message", e.getMessage());
        return map;
    }

    @ExceptionHandler(value = Exception.class)
    public Map<String, Object> defaultErrorHandler(Exception e){
        logger.error("自訂業務失敗", e);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 500);
        map.put("message", e.getMessage());
        return map;
    }
}
