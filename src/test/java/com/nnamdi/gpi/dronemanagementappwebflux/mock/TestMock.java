package com.nnamdi.gpi.dronemanagementappwebflux.mock;



import com.nnamdi.gpi.dronemanagementappwebflux.dto.DroneDto;
import com.nnamdi.gpi.dronemanagementappwebflux.dto.Response;
import com.nnamdi.gpi.dronemanagementappwebflux.dto.ResponseCodes;
import com.nnamdi.gpi.dronemanagementappwebflux.model.Drone;
import com.nnamdi.gpi.dronemanagementappwebflux.request.RegisterDroneDto;
import com.nnamdi.gpi.dronemanagementappwebflux.util.ConstantsUtil;
import com.nnamdi.gpi.dronemanagementappwebflux.util.Direction;

import java.time.ZonedDateTime;

public class TestMock {
    public static final String ID = "8D19B947443D4C1BB2700337527BC251";
    public static final String DRONE_NAME = "DRONE";

    public static RegisterDroneDto registerDroneDto() {
        return new RegisterDroneDto(4, 4,Direction.WEST, DRONE_NAME );

    }

    public static RegisterDroneDto registerDroneBadRequestDto() {
        return new RegisterDroneDto(-10, 10, Direction.WEST, DRONE_NAME);
    }


    public static DroneDto buildDroneDto() {
        return new DroneDto(ID,4,4,DRONE_NAME,Direction.WEST);
    }



    public static Drone buildDrone(RegisterDroneDto droneDto) {
        Drone drone = Drone.builder().name(droneDto.getName()).direction(droneDto.getDirection()).coordinateX(droneDto.getCoordinateX()).coordinateY(droneDto.getCoordinateY()).build();
        drone.setId(ID);
        drone.setCreatedDate(ZonedDateTime.now());
        drone.setLastModifiedDate(ZonedDateTime.now());
        return drone;
    }

    public static <T> Response<T> buildResponse(T data) {
        return new Response<>(ResponseCodes.SUCCESS.code(), ConstantsUtil.SUCCESSFUL, data, "");
    }

}
