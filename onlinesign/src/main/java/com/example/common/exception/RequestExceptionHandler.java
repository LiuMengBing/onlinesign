package com.example.common.exception;

import com.example.common.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RequestExceptionHandler {

    // 表单验证错误,报400
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseModel paramErrorHandler(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> errors = fieldErrors.stream()
                .map(error -> error.getField() + error.getDefaultMessage())
                .collect(Collectors.toList());

        return ResponseModel.newFailure(HttpStatus.BAD_REQUEST, errors.toString());
    }

    // IO错误报500
    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseModel ioExceptionHandler(IOException e) {
        return ResponseModel.newFailure(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    // 自定义异常500
    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseModel okExceptionHandler(CustomException e) {
        return ResponseModel.newFailure(e.getCode(), e.getMessage() ,e.getData());
    }

    /**
     * 业务异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseModel bizExceptionHandler(BizException e) {
        return ResponseModel.newFailure(e.getCode(), e.getMessage(), e.getData());
    }

}