package com.github.son_daehyeon.common.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "kakao.oauth")
public class KakaoProperty {

	@NotBlank
	private String clientId;

	@NotBlank
	private String redirectUri;
}
