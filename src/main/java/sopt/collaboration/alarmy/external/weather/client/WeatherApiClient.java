package sopt.collaboration.alarmy.external.weather.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sopt.collaboration.alarmy.external.weather.dto.response.ForecastDataResponse;

@FeignClient(name = "weatherApiClient", url = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0")
public interface WeatherApiClient {
    @GetMapping(value = "/getVilageFcst", consumes = "application/json")
    ForecastDataResponse getWeatherData(@RequestParam("ServiceKey") String serviceKey,
                                        @RequestParam("pageNo") int pageNo,
                                        @RequestParam("numOfRows") int numOfRows,
                                        @RequestParam("dataType") String dataType,
                                        @RequestParam("base_date") String baseDate,
                                        @RequestParam("base_time") String baseTime,
                                        @RequestParam("nx") int nx,
                                        @RequestParam("ny") int ny);
}
