package sopt.collaboration.alarmy.alarm.dto.response;

public record AlarmResponse(
        Long id,
        String timestamp,
        boolean isActive
) {}
