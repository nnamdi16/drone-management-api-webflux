package com.nnamdi.gpi.dronemanagementappwebflux.repository;

import com.nnamdi.gpi.dronemanagementappwebflux.model.Drone;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface DroneRepository extends ReactiveCrudRepository<Drone, String> {

    @Query("SELECT * FROM drones WHERE coordinatex = $1 AND coordinatey = $2 OR (LOWER(name) = LOWER($3))")
    Mono<Drone> findByCoordinatesOrName(@Param("coordinateX") int coordinateX, @Param("coordinateY") int coordinateY, String name);
    Flux<Drone> findAllBy(Pageable pageable);
}
