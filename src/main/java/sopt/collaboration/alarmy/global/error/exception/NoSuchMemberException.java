package sopt.collaboration.alarmy.global.error.exception;

import sopt.collaboration.alarmy.global.error.ErrorCode;

public class NoSuchMemberException extends BusinessException{
    public NoSuchMemberException(ErrorCode errorCode) {
        super(ErrorCode.NOT_FOUND_MEMBER);
    }
}
