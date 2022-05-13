package com.ssafy.happyhouse.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "요청에 오류가 있습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "요청한 자원이 존재하지 않습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류가 발생했습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "잘못된 요청입니다."),

    HISTORY_NOT_FOUND(HttpStatus.NOT_FOUND, "거래 내역이 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}