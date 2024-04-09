package com.nnamdi.gpi.dronemanagementappwebflux.service;


import com.nnamdi.gpi.dronemanagementappwebflux.dto.DroneDto;
import com.nnamdi.gpi.dronemanagementappwebflux.model.Drone;
import com.nnamdi.gpi.dronemanagementappwebflux.request.RegisterDroneDto;
import com.nnamdi.gpi.dronemanagementappwebflux.request.UpdateDronePositionDto;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * Drone Service
 */
public interface DroneService {
    Mono<DroneDto> registerDrone(RegisterDroneDto drone);

    DroneDto moveDrone(String id, UpdateDronePositionDto updateDroneDto);

    Optional<Drone> getDronePosition(String id);

    Mono<Page<Drone>> getDrones(int page, int limit);
}
