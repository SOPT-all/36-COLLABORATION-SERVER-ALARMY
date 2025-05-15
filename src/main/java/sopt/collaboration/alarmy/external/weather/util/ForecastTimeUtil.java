package sopt.collaboration.alarmy.external.weather.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ForecastTimeUtil {

    /**
     * 기상청 API를 사용하기 위해 현재 시각에 맞는 basedate, basetime을 얻기 위한 함수
     * 기상청 단기 예보 발표 시각은 02시부터 3시간 간격
     * API 사용 가능 시간은 해당 시각의 10분 부터
     * 따라서 시스템 안정성을 위해 해당 시각의 20분부터 조회할 수 있도록 설계
     * @return LocalDateTime baseDateTime
     */
    public static LocalDateTime getForecastBaseDateTime(){
        LocalDateTime time = LocalDateTime.now();

        int hour = time.getHour();
        int minute = time.getMinute();

        for(int i = 23; i >= 2; i-=3){
            if((hour == i && minute > 20) || i < hour){
                return LocalDateTime.of(
                        time.getYear(),
                        time.getMonthValue(),
                        time.getDayOfMonth(),
                        i,
                        0
                );
            }
        }

        return LocalDateTime.of(
                time.getYear(),
                time.getMonthValue(),
                time.getDayOfMonth(),
                23,
                0
        ).minusDays(1);
    }

    public static String getForecastBaseTime(LocalDateTime time){
        return String.format("%02d00", time.getHour());
    }

    public static String getForecastBaseDate(LocalDateTime time){
        return String.format("%04d%02d%02d", time.getYear(), time.getMonthValue(), time.getDayOfMonth());
    }
}
