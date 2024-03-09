package com.anderson.edescart.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BingMapsConfig {

	@Value("${bingmaps.api.key}")
	String bingApiKey;
	@Value("${bingmaps.api.url}")
	String apiUrl;


	@Bean
	WebClient webClient(WebClient.Builder builder) {
		return builder.baseUrl(apiUrl).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}

	public String getBingApiKey() {
		return bingApiKey;
	}

	public String getApiUrl() {
		return apiUrl;
	}

}
