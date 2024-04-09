package com.nnamdi.gpi.dronemanagementappwebflux.controller;

import com.nnamdi.gpi.dronemanagementappwebflux.dto.Response;
import com.nnamdi.gpi.dronemanagementappwebflux.request.RegisterDroneDto;
import com.nnamdi.gpi.dronemanagementappwebflux.service.DroneService;
import com.nnamdi.gpi.dronemanagementappwebflux.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URI;

import static com.nnamdi.gpi.dronemanagementappwebflux.controller.BaseApiController.BASE_API_PATH;
import static com.nnamdi.gpi.dronemanagementappwebflux.controller.BaseApiController.DRONE;

@RestController
@Slf4j
@RequestMapping(BASE_API_PATH + DRONE)
public class DroneController {
    private final DroneService droneService;
    private final ResponseUtil responseUtil;

    public DroneController(DroneService droneService, ResponseUtil responseUtil) {
        this.droneService = droneService;
        this.responseUtil = responseUtil;
    }


    @PostMapping
    public Mono<ResponseEntity<Response>> createTask(@Valid @RequestBody RegisterDroneDto requestDto) {
        return Mono.just(requestDto)
                .flatMap(droneService::registerDrone)
                .map(responseUtil::getSuccessResponse)
                .map(response -> ResponseEntity.created(URI.create(BASE_API_PATH + DRONE)).body(response));


    }


    @GetMapping
    public Mono<ResponseEntity<Response>> getTasks(@RequestParam(value = "page", required = false, defaultValue = "1") int page, @RequestParam(value = "limit", required = false, defaultValue = "50") int limit) {
        return droneService.getDrones(page,limit).map(responseUtil::getSuccessResponse).map(ResponseEntity::ok);
    }

}
