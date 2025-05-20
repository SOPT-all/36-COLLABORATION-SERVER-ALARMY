package sopt.collaboration.alarmy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableFeignClients(basePackages = "sopt.collaboration.alarmy.external.weather.client")
public class AlarmyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlarmyApplication.class, args);
	}

}
