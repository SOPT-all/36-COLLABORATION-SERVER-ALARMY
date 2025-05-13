package sopt.collaboration.alarmy.global.error;

import lombok.Getter;

@Getter
public record ErrorResponse(boolean success, int code, String message) {

    public static ErrorResponse of(ErrorCode errorCode){
        return new ErrorResponse(
                errorCode.isSuccess(),
                errorCode.getCode(),
                errorCode.getMessage());
    }
}
