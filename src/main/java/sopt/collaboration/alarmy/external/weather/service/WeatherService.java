package sopt.collaboration.alarmy.external.weather.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sopt.collaboration.alarmy.external.weather.client.WeatherApiClient;
import sopt.collaboration.alarmy.external.weather.config.ForecastProperties;
import sopt.collaboration.alarmy.external.weather.dto.response.ForecastDataResponse;
import sopt.collaboration.alarmy.external.weather.dto.response.WeatherResponse;
import sopt.collaboration.alarmy.external.weather.util.ForecastTimeUtil;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherService {

    private final WeatherApiClient client;

    private final ForecastProperties forecastProperties;

    public WeatherResponse weather(){
        LocalDateTime now = ForecastTimeUtil.getForecastBaseDateTime();
        String forecastBaseDate = ForecastTimeUtil.getForecastBaseDate(now);
        String forecastBaseTime = ForecastTimeUtil.getForecastBaseTime(now);

        log.info("forecast properties={}", forecastProperties);
        log.info("basedate={}, basetime={}", forecastBaseDate, forecastBaseTime);

        ForecastDataResponse forecastData = client.getWeatherData(
                forecastProperties.getServiceKey(),
                forecastProperties.getPageNo(),
                forecastProperties.getNumOfRows(),
                forecastProperties.getDataType(),
                forecastBaseDate,
                forecastBaseTime,
                forecastProperties.getNx(),
                forecastProperties.getNy());

        return WeatherResponse.from(forecastData);
    }
}
