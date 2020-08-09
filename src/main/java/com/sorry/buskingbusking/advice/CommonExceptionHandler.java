package com.sorry.buskingbusking.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.sorry.buskingbusking.controller")
public class CommonExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String handlerRuntimeException(){
        return "error/commonException";
    }
}
