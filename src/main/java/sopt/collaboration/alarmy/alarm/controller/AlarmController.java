package sopt.collaboration.alarmy.alarm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sopt.collaboration.alarmy.alarm.dto.request.AlarmCheckRequest;
import sopt.collaboration.alarmy.alarm.dto.request.AlarmRequest;
import sopt.collaboration.alarmy.alarm.dto.response.AlarmCheckListResponse;
import sopt.collaboration.alarmy.alarm.dto.response.AlarmResponse;
import sopt.collaboration.alarmy.alarm.service.AlarmService;
import sopt.collaboration.alarmy.global.result.ResultCode;
import sopt.collaboration.alarmy.global.result.ResultResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AlarmController {

    private final AlarmService alarmService;

    @Operation(
            summary = "알람 생성",
            description = """
                사용자의 알람을 생성합니다.
                ### 요청 예시 JSON:
                ```json
                {
                  "timestamp": "16:20"
                }
                ```
                userId는 Request Header로 전달해야 합니다.
                """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "알람 생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 형식의 timestamp / 요청 헤더 값 오류"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 유저 / 잘못된 URL"),
            @ApiResponse(responseCode = "405", description = "지원하지 않는 HTTP 메서드"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류 발생")
    })
    @PostMapping("/alarm")
    public ResponseEntity<ResultResponse<List<AlarmResponse>>> createAlarm(
            @RequestHeader("userId") long userId,
            @RequestBody @Valid AlarmRequest request
    ) {
        List<AlarmResponse> response = alarmService.createAlarm(userId, request);
        return ResponseEntity.status(ResultCode.ALARM_CREATE_SUCCESS.getStatus())
                .body(ResultResponse.of(ResultCode.ALARM_CREATE_SUCCESS, response));
    }

    @Operation(
            summary = "알람 전체 조회",
            description = """
                사용자의 모든 알람을 조회합니다.
                userId는 Request Header로 전달해야 합니다.
                """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "알람 전체 조회 성공"),
            @ApiResponse(responseCode = "400", description = "요청 헤더 값 오류"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 유저 / 잘못된 URL"),
            @ApiResponse(responseCode = "405", description = "지원하지 않는 HTTP 메서드"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류 발생")
    })
    @GetMapping("/alarms")
    public ResponseEntity<ResultResponse<List<AlarmResponse>>> getAllAlarms(
            @RequestHeader("userId") long userId
    ) {
        List<AlarmResponse> response = alarmService.getAllAlarms(userId);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.ALARM_FETCH_SUCCESS, response));
    }

    @Operation(
            summary = "알람 시간 체크",
            description = """
                현재 시각과 일치하는 알람의 존재 여부를 확인합니다.
                ### 요청 예시 JSON:
                ```json
                {
                  "currentTime": "2025-05-11T11:58:20.551705"
                }
                ```
                userId는 Request Header로 전달해야 합니다.
                """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "알람 시간 체크 성공"),
            @ApiResponse(responseCode = "400", description = "요청 헤더 값 오류"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 유저 / 잘못된 URL"),
            @ApiResponse(responseCode = "405", description = "지원하지 않는 HTTP 메서드"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류 발생")
    })
    @GetMapping("/alarm/check")
    public ResponseEntity<ResultResponse<AlarmCheckListResponse>> getAlarmCheck(
            @RequestHeader("Authorization") long userId,
            @RequestBody AlarmCheckRequest alarmCheckRequest
    ) {
        AlarmCheckListResponse alarmInfo = alarmService.getTimeCheckAlarm(userId, alarmCheckRequest);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.ALARM_CHECK_TIME_SUCCESS, alarmInfo));
    }
}