package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.dto.ErrorDto;
import com.ssafy.happyhouse.dto.ErrorResponseDto;
import com.ssafy.happyhouse.exception.ApplicationException;
import com.ssafy.happyhouse.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNoHandlerFoundException(NoHandlerFoundException e) {
        log.error("MethodArgumentNotValidException: {}", e.getMessage());
        ErrorCode errorCode = ErrorCode.BAD_REQUEST;
        ErrorDto errorDto = new ErrorDto(errorCode.getHttpStatus(), errorCode.getMessage());
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponseDto.from(errorDto));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("MethodArgumentTypeMismatchException: {}", e.getMessage());
        ErrorCode errorCode = ErrorCode.BAD_REQUEST;
        ErrorDto errorDto = new ErrorDto(errorCode.getHttpStatus(), errorCode.getMessage());
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponseDto.from(errorDto));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDto> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException: {}", e.getMessage());
        ErrorCode errorCode = ErrorCode.METHOD_NOT_ALLOWED;
        ErrorDto errorDto = new ErrorDto(errorCode.getHttpStatus(), errorCode.getMessage());
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponseDto.from(errorDto));
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomException(ApplicationException e) {
        ErrorCode errorCode = e.getErrorCode();
        log.error("handleCustomException: {}", errorCode.getHttpStatus() + " " + errorCode.getMessage());
        ErrorDto errorDto = new ErrorDto(errorCode.getHttpStatus(), errorCode.getMessage());
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponseDto.from(errorDto));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception e) {
        log.error("handleException", e);
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        ErrorDto errorDto = new ErrorDto(errorCode.getHttpStatus(), errorCode.getMessage());
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponseDto.from(errorDto));
    }
}
