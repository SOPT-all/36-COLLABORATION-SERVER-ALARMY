package sopt.collaboration.alarmy.external.weather.dto.response;

import lombok.Data;
import sopt.collaboration.alarmy.external.weather.dto.response.enums.WeatherCode;
import sopt.collaboration.alarmy.global.error.exception.WeatherCodeParsingException;

import java.util.List;
import java.util.NoSuchElementException;

@Data
public class ForecastDataResponse {

    private Response response;

    @Data
    public static class Response {
        private Header header;
        private Body body;
    }

    @Data
    public static class Header {
        private String resultCode;
        private String resultMsg;
    }

    @Data
    public static class Body {
        private String dataType;
        private Items items;
        private int pageNo;
        private int numOfRows;
        private int totalCount;
    }

    @Data
    public static class Items {
        private List<Item> item;
    }

    @Data
    public static class Item {
        private String baseDate;
        private String baseTime;
        private String category;
        private String fcstDate;
        private String fcstTime;
        private String fcstValue;
        private int nx;
        private int ny;
    }

    public WeatherCode getWeatherCode(){
        Items items = this.response.body.items;
        Item sky = items.getItem().stream()
                .filter(item -> item.category.equals("SKY"))
                .findFirst().orElseThrow(WeatherCodeParsingException::new);
        Item rainCondition = items.getItem().stream()
                .filter(item -> item.category.equals("PCP"))
                .findFirst().orElseThrow(WeatherCodeParsingException::new);

        if(!rainCondition.fcstValue.equals("강수없음")) return WeatherCode.RAINY;
        if(sky.fcstValue.equals("4")) return WeatherCode.CLOUDY;
        return WeatherCode.fromCode(Integer.parseInt(sky.fcstValue));

    }

    public int getTemperature(){
        Item tmp = this.response.body.items.getItem().stream()
                .filter(item -> item.category.equals("TMP"))
                .findFirst().orElseThrow(WeatherCodeParsingException::new);
        return Integer.parseInt(tmp.fcstValue);
    }


}
