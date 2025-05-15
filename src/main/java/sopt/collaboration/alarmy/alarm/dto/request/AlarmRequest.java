package sopt.collaboration.alarmy.alarm.dto.request;

import jakarta.validation.constraints.Pattern;

public record AlarmRequest(

        // 00:00부터 23:59까지의 24시간 형식 시간만 허용
        @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$", message = "잘못된 형식의 timestamp입니다.")
        String timestamp
) {}
