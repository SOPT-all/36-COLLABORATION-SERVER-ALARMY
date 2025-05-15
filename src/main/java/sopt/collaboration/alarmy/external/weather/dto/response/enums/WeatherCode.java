package sopt.collaboration.alarmy.external.weather.dto.response.enums;

import lombok.Getter;

@Getter
public enum WeatherCode {
    CLEAR(1, "맑음"),
    RAINY(2, "비옴"),
    CLOUDY(3, "흐림");

    private final int code;
    private final String description;

    WeatherCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static WeatherCode fromCode(int code) {
        for (WeatherCode condition : values()) {
            if (condition.code == code) return condition;
        }
        // 예외 커스텀 해줘야 함
        throw new IllegalArgumentException("Unknown sky condition code: " + code);
    }

    @Override
    public String toString() {
        return String.valueOf(this.code);
    }
}
