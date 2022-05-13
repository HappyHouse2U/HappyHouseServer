package com.ssafy.happyhouse.exception;

public class MemberNotFoundException extends ApplicationException  {
    public MemberNotFoundException() {
        super(ErrorCode.MEMBER_NOT_FOUND);
    }
}
