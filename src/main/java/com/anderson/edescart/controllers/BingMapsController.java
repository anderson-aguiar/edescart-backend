package com.anderson.edescart.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.anderson.edescart.config.BingMapsConfig;
import com.anderson.edescart.dto.BingMapsResponseDTO;
import com.anderson.edescart.services.exceptions.WebClientException;

import reactor.core.publisher.Mono;

@RestController
public class BingMapsController {

	@Autowired
	private BingMapsConfig bingMapsConfig;

	@Autowired
	private WebClient webClient;

	public List<Double> geolocation(String postalCode) {
		List<Double> coordinates = new ArrayList<>();
		try {
			String url = bingMapsConfig.getApiUrl() + "?postalCode=" + postalCode + "&key="
					+ bingMapsConfig.getBingApiKey() + "&output=json";
			Mono<BingMapsResponseDTO> monoBingMaps = this.webClient.method(HttpMethod.GET).uri(url).retrieve()
					.bodyToMono(BingMapsResponseDTO.class);
			BingMapsResponseDTO dto = monoBingMaps.block();
			double latitude = dto.getResourceSets().get(0).getResources().get(0).getPoint().getCoordinates()[0];
			double longitude = dto.getResourceSets().get(0).getResources().get(0).getPoint().getCoordinates()[1];
			coordinates.add(latitude);
			coordinates.add(longitude);
			return coordinates;
		} catch (Exception e) {
			throw new WebClientException("Recurso não encontrado");
		}

	}

}
