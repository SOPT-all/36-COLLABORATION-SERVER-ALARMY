package sopt.collaboration.alarmy.global.result;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResultCode {

    ALARM_CREATE_SUCCESS(HttpStatus.CREATED.value(), HttpStatus.CREATED, "알람 생성 성공"),
    ALARM_FETCH_ALL_SUCCESS(HttpStatus.OK.value(), HttpStatus.OK, "알람 전체 조회 성공"),
    ALARM_CHECK_TIME_SUCCESS(HttpStatus.OK.value(), HttpStatus.OK, "일어나야지"),
    TODAY_SENTENCE_FETCH_SUCCESS(HttpStatus.OK.value(), HttpStatus.OK, "오늘의 문장 생성 성공"),
    WEATHER_FETCH_SUCCESS(HttpStatus.OK.value(), HttpStatus.OK, "날씨 조회 성공");

    private final boolean success;
    private final int code;
    private final HttpStatus status;
    private final String message;

    ResultCode(int code, HttpStatus status, String message) {
        this.success = true;
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
