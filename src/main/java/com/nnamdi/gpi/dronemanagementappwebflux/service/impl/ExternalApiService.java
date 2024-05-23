package com.nnamdi.gpi.dronemanagementappwebflux.service.impl;

import com.nnamdi.gpi.dronemanagementappwebflux.dto.Data;
import com.nnamdi.gpi.dronemanagementappwebflux.dto.DroneDto;
import com.nnamdi.gpi.dronemanagementappwebflux.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class ExternalApiService {
    private  final WebClient webClient;


    public ExternalApiService(WebClient.Builder webClient, @Value("${external.api.base-url}") String baseUrl) {
        this.webClient = webClient.baseUrl(baseUrl).build();
    }

    public Mono<PageImpl<DroneDto>> getDrones(int page, int limit) {

        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/v1/drone")
                        .queryParam("page", page)
                        .queryParam("limit", limit)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Response<Data<DroneDto>>>() {})
                .map(response -> {
                    List<DroneDto> drones = response.data().getContent();
                    PageRequest pageRequest = PageRequest.of(page, limit);
                    return new PageImpl<>(drones, pageRequest, drones.size());
                });
    }
}
