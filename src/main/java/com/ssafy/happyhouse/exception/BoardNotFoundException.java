package com.ssafy.happyhouse.exception;

public class BoardNotFoundException extends ApplicationException {
    public BoardNotFoundException() {
        super(ErrorCode.BOARD_NOT_FOUND);
    }
}
