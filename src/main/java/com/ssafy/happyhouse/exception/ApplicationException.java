package com.ssafy.happyhouse.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class ApplicationException extends RuntimeException {
    private final ErrorCode errorCode;
}