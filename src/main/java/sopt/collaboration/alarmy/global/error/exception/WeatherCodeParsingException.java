package sopt.collaboration.alarmy.global.error.exception;

import sopt.collaboration.alarmy.global.error.ErrorCode;

public class WeatherCodeParsingException extends BusinessException{
    public WeatherCodeParsingException() {
        super(ErrorCode.WEATHER_CODE_PARSING_ERROR);
    }
}
