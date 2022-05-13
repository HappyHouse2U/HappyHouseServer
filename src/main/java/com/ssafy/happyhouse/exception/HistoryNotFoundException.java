package com.ssafy.happyhouse.exception;

public class HistoryNotFoundException extends ApplicationException {
    public HistoryNotFoundException() {
        super(ErrorCode.HISTORY_NOT_FOUND);
    }
}
