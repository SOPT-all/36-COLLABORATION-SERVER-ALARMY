package sopt.collaboration.alarmy.global.error.exception;

import sopt.collaboration.alarmy.global.error.ErrorCode;

public class NotValidTimeStampException extends BusinessException{
    public NotValidTimeStampException() {
        super(ErrorCode.NOT_VALID_TIMESTAMP);
    }
}
