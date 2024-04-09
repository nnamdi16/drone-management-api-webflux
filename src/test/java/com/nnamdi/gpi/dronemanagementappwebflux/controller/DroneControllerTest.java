package com.nnamdi.gpi.dronemanagementappwebflux.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnamdi.gpi.dronemanagementappwebflux.dto.DroneDto;
import com.nnamdi.gpi.dronemanagementappwebflux.dto.Response;
import com.nnamdi.gpi.dronemanagementappwebflux.model.Drone;
import com.nnamdi.gpi.dronemanagementappwebflux.request.RegisterDroneDto;
import com.nnamdi.gpi.dronemanagementappwebflux.service.DroneService;
import com.nnamdi.gpi.dronemanagementappwebflux.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Objects;

import static com.nnamdi.gpi.dronemanagementappwebflux.controller.BaseApiController.BASE_API_PATH;
import static com.nnamdi.gpi.dronemanagementappwebflux.controller.BaseApiController.DRONE;
import static com.nnamdi.gpi.dronemanagementappwebflux.mock.TestMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@WebFluxTest(DroneController.class)
@Slf4j
public class DroneControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private DroneService droneService;

    @MockBean
    ResponseUtil responseUtil;

    @Autowired
    ObjectMapper objectMapper;



    @Test
    void testRegisterDrone() throws IOException {


        Mono<DroneDto> droneDto = droneService.registerDrone(any(RegisterDroneDto.class));
        when(droneDto).thenReturn(Mono.just(buildDroneDto()));
        when(responseUtil.getSuccessResponse(any())).thenReturn(buildResponse(buildDroneDto()));

        RegisterDroneDto registerDroneDto = registerDroneDto();

        webTestClient.post()
                .uri(BASE_API_PATH + DRONE)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(registerDroneDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Drone.class)
                .consumeWith(drone -> {
                    String dataString = new String(Objects.requireNonNull(drone.getResponseBodyContent()));
                    Response data;
                    try {
                        data = objectMapper.readValue(dataString, Response.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        assertEquals(objectMapper.writeValueAsString(data.data()), objectMapper.writeValueAsString(buildResponse(buildDroneDto()).data()));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                });



        Mockito.verify(droneService, times(1)).registerDrone(registerDroneDto());





    }
}
