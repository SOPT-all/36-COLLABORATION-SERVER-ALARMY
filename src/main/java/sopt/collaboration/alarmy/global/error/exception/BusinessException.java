package sopt.collaboration.alarmy.global.error.exception;

import lombok.Getter;
import sopt.collaboration.alarmy.global.error.ErrorCode;

@Getter
public class BusinessException extends RuntimeException{
    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
