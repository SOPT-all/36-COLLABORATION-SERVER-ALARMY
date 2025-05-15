package sopt.collaboration.alarmy.external.weather.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "forecast")
@Data
public class ForecastProperties {
    private String serviceKey;
    private int pageNo;
    private int numOfRows;
    private String dataType;
    private int nx;
    private int ny;
}
