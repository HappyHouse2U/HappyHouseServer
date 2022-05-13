package com.ssafy.happyhouse.controller;

import com.example.happyhouse5.exception.ApplicationException;
import com.example.happyhouse5.exception.ErrorCode;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionAdvice {
    private final ModelAndView mav = new ModelAndView("error");

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleNoHandlerFoundException(NoHandlerFoundException e) {
        e.printStackTrace();
        ErrorCode errorCode = ErrorCode.NOT_FOUND;
        mav.addObject("errorCode", errorCode);
        mav.addObject("errorMsg", errorCode.getMessage());
        return mav;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ModelAndView handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        e.printStackTrace();
        ErrorCode errorCode = ErrorCode.BAD_REQUEST;
        mav.addObject("errorCode", errorCode);
        mav.addObject("errorMsg", errorCode.getMessage());
        return mav;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ModelAndView handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        e.printStackTrace();
        ErrorCode errorCode = ErrorCode.METHOD_NOT_ALLOWED;
        mav.addObject("errorCode", errorCode);
        mav.addObject("errorMsg", errorCode.getMessage());
        return mav;
    }

    @ExceptionHandler(ApplicationException.class)
    public ModelAndView handleCustomException(ApplicationException e) {
        e.printStackTrace();
        mav.addObject("errorCode", e.getErrorCode());
        mav.addObject("errorMsg", e.getErrorCode().getMessage());
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
        e.printStackTrace();
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        mav.addObject("errorCode", errorCode);
        mav.addObject("errorMsg", errorCode.getMessage());
        return mav;
    }
}
