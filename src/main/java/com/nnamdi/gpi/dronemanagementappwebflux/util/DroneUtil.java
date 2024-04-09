package com.nnamdi.gpi.dronemanagementappwebflux.util;


import com.nnamdi.gpi.dronemanagementappwebflux.model.Drone;
import com.nnamdi.gpi.dronemanagementappwebflux.request.RegisterDroneDto;
import com.nnamdi.gpi.dronemanagementappwebflux.request.UpdateDronePositionDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;


@Component
public class DroneUtil {
    private final ModelMapper modelMapper;

    @Autowired
    public DroneUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Drone buildDroneEntity(RegisterDroneDto droneDto) {
        Drone drone =  modelMapper.map(droneDto, Drone.class);
        drone.setId(AppUtil.generateUUIDString());
        drone.setCreatedDate(ZonedDateTime.now());
        drone.setLastModifiedDate(ZonedDateTime.now());
        return drone;
    }

    public boolean isValidDirectionChange(Drone currentDrone, UpdateDronePositionDto newPosition) {
        Direction currentDirection = currentDrone.getDirection();
        int currentCoordinateY = currentDrone.getCoordinateY();
        return (currentDirection != Direction.NORTH || newPosition.getCoordinateY() >= currentCoordinateY) &&
                (currentDirection != Direction.SOUTH || newPosition.getCoordinateY() <= currentCoordinateY);
    }
}
