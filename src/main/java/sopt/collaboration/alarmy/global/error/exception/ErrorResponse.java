package sopt.collaboration.alarmy.global.error.exception;

import lombok.Getter;
import sopt.collaboration.alarmy.global.error.ErrorCode;

@Getter
public class ErrorResponse {

    private final boolean success;
    private final int code;
    private final String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.success = errorCode.isSuccess();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public static ErrorResponse of(ErrorCode errorCode){
        return new ErrorResponse(errorCode);
    }
}
