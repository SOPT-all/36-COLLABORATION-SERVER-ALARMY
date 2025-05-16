package sopt.collaboration.alarmy.external.weather.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sopt.collaboration.alarmy.external.weather.dto.response.WeatherResponse;
import sopt.collaboration.alarmy.external.weather.service.WeatherService;
import sopt.collaboration.alarmy.global.result.ResultCode;
import sopt.collaboration.alarmy.global.result.ResultResponse;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @Operation(
            summary = "날씨 정보 조회",
            description = """
                하늘 상태, 기온 등 날씨 정보를 조회합니다.
                """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "날씨 정보 조회 성공"),
            @ApiResponse(responseCode = "422", description = "날씨 코드 파싱 실패"),
            @ApiResponse(responseCode = "502", description = "기상청 API 조회 실패 || 날씨 정보 식별 실패"),
            @ApiResponse(responseCode = "404", description = "잘못된 URL"),
            @ApiResponse(responseCode = "405", description = "지원하지 않는 HTTP 메서드"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류 발생")
    })
    @GetMapping("/weather")
    public ResponseEntity<ResultResponse<WeatherResponse>> weatherInfo(){
        return ResponseEntity.ok(ResultResponse.of(ResultCode.WEATHER_FETCH_SUCCESS, weatherService.weather()));
    }
}
