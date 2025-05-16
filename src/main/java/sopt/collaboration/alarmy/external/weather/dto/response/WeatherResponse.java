package sopt.collaboration.alarmy.external.weather.dto.response;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public record WeatherResponse(String date, int temperature, int weatherCode) {

    public static WeatherResponse from(ForecastDataResponse data){
        LocalDate now = LocalDate.now();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        String dayOfWeek = now.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN);
        String date = String.format("%d월 %d일 %s", month, day, dayOfWeek);
        return new WeatherResponse(date, data.getTemperature(), data.getWeatherCode().getCode());
    }
}
