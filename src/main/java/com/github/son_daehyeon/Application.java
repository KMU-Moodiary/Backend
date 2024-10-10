package com.github.son_daehyeon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import com.github.son_daehyeon.common.property.JwtProperty;
import com.github.son_daehyeon.common.property.KakaoProperty;

@SpringBootApplication
@EnableMongoAuditing
@EnableConfigurationProperties({JwtProperty.class, KakaoProperty.class})
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}
}
