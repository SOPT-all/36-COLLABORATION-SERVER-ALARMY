package sopt.collaboration.alarmy.alarm.dto.response;

import java.util.List;

public record AlarmCheckListResponse(
        List<AlarmCheckResponse> alarmInfo
) {
}
