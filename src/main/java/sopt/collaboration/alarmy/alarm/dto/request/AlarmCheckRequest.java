package sopt.collaboration.alarmy.alarm.dto.request;

import java.time.LocalDateTime;

public record AlarmCheckRequest(
        LocalDateTime currentTime
) {
}
