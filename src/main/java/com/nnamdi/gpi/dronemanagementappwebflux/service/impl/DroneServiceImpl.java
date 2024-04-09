package com.nnamdi.gpi.dronemanagementappwebflux.service.impl;


import com.nnamdi.gpi.dronemanagementappwebflux.dto.DroneDto;
import com.nnamdi.gpi.dronemanagementappwebflux.exception.ModelAlreadyExistException;
import com.nnamdi.gpi.dronemanagementappwebflux.model.Drone;
import com.nnamdi.gpi.dronemanagementappwebflux.provider.MessageProvider;
import com.nnamdi.gpi.dronemanagementappwebflux.repository.DroneRepository;
import com.nnamdi.gpi.dronemanagementappwebflux.request.RegisterDroneDto;
import com.nnamdi.gpi.dronemanagementappwebflux.request.UpdateDronePositionDto;
import com.nnamdi.gpi.dronemanagementappwebflux.service.DroneService;
import com.nnamdi.gpi.dronemanagementappwebflux.util.AppUtil;
import com.nnamdi.gpi.dronemanagementappwebflux.util.DroneUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class DroneServiceImpl implements DroneService {
    private final ModelMapper modelMapper;
    private final DroneUtil droneUtil;
    private final DroneRepository droneRepository;
    private final MessageProvider messageProvider;

    @Override
    public DroneDto moveDrone(String id, UpdateDronePositionDto updateDroneDto) {
        return null;
    }

    @Override
    public Optional<Drone> getDronePosition(String id) {
        return Optional.empty();
    }

    @Override
    public Mono<Page<Drone>> getDrones(int page, int limit) {
        log.info("about to retrieve all drones by pagination {}, {}", page, limit);
        AppUtil.validatePageRequest(page, limit);
        Pageable pageable = PageRequest.of(page - 1, limit);
        return droneRepository.findAllBy(pageable).collectList()
                .zipWith(this.droneRepository.count())
                .map(drone -> new PageImpl<>(drone.getT1(), pageable, drone.getT2()));

    }


    @Override
    public Mono<DroneDto> registerDrone(RegisterDroneDto droneDto) {
        log.info("about to register a drone {}", droneDto);

       return droneRepository.findByCoordinatesOrName(droneDto.getCoordinateX(), droneDto.getCoordinateY(), droneDto.getName())
               .singleOptional()
               .flatMap(existingDrone -> {
       if (existingDrone.isPresent()) {
           log.error("Drone already exists: {}", existingDrone);
           return Mono.error(new ModelAlreadyExistException(messageProvider.getDroneAlreadyExist(String.valueOf(droneDto.getCoordinateX()), String.valueOf(droneDto.getCoordinateY()), droneDto.getName())));
       }
        Drone drone = droneUtil.buildDroneEntity(droneDto);

        return droneRepository.save(drone)
                .map(savedDrone -> {
                    log.info("Drone persisted successfully: {}", savedDrone);
                return modelMapper.map(savedDrone, DroneDto.class);
                });


       });

    }


}
