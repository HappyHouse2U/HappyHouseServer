package com.ssafy.happyhouse.exception;

public class MemberIdDuplicatedException extends ApplicationException {
    public MemberIdDuplicatedException() {
        super(ErrorCode.MEMBER_ID_DUPLICATED);
    }
}
