package com.nnamdi.gpi.dronemanagementappwebflux.service;


import com.nnamdi.gpi.dronemanagementappwebflux.dto.DroneDto;
import com.nnamdi.gpi.dronemanagementappwebflux.model.Drone;
import com.nnamdi.gpi.dronemanagementappwebflux.request.RegisterDroneDto;
import com.nnamdi.gpi.dronemanagementappwebflux.request.UpdateDronePositionDto;
import org.springframework.data.domain.PageImpl;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * Drone Service
 */
public interface DroneService {
    Mono<DroneDto> registerDrone(RegisterDroneDto drone);

    DroneDto moveDrone(String id, UpdateDronePositionDto updateDroneDto);

    Optional<Drone> getDronePosition(String id);

    Mono<PageImpl<DroneDto>> getDrones(int page, int limit);
}
