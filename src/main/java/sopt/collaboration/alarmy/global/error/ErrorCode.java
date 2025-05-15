package sopt.collaboration.alarmy.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    /**
     * 알람 관련 예외
     */
    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다."),
    NOT_VALID_TIMESTAMP(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, "잘못된 형식의 timestamp입니다."),

    /**
     * 날씨 API 관련 예외
     */
    WEATHER_CODE_PARSING_ERROR(HttpStatus.UNPROCESSABLE_ENTITY.value(), HttpStatus.UNPROCESSABLE_ENTITY, "날씨 코드 파싱 실패"),
    UNKNOWN_WEATHER_CODE_ERROR(HttpStatus.BAD_GATEWAY.value(), HttpStatus.BAD_GATEWAY, "날씨 코드 식별 실패"),
    EXTERNAL_API_CLIENT_ERROR(HttpStatus.BAD_GATEWAY.value(), HttpStatus.BAD_GATEWAY, "외부 API 호출 실패"),

    /**
     * 공통 예외
     */
    NOT_SUPPORTED_METHOD_ERROR(HttpStatus.METHOD_NOT_ALLOWED.value(), HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 HTTP 메서드입니다."),
    NOT_FOUND_ERROR(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, "지원하지 않는 URL입니다."),
    NOT_VALID_HEADER(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, "요청 헤더의 값이 잘못된 형식입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류 발생");

    private final boolean success;
    private final int code;
    private final HttpStatus status;
    private final String message;

    ErrorCode(int code, HttpStatus status, String message) {
        this.success = false;
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
