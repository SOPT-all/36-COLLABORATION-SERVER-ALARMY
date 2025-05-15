package sopt.collaboration.alarmy.global.error.exception;

import sopt.collaboration.alarmy.global.error.ErrorCode;

public class UnknownWeatherCodeException extends BusinessException{
    public UnknownWeatherCodeException() {
        super(ErrorCode.UNKNOWN_WEATHER_CODE_ERROR);
    }
}
