package sopt.collaboration.alarmy.alarm.dto.response;

public record AlarmCheckResponse(
        Long id,
        boolean shouldTrigger
) {
}
