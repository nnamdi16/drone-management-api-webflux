package com.nnamdi.gpi.dronemanagementappwebflux.service;

import com.nnamdi.gpi.dronemanagementappwebflux.model.Drone;
import com.nnamdi.gpi.dronemanagementappwebflux.provider.MessageProvider;
import com.nnamdi.gpi.dronemanagementappwebflux.repository.DroneRepository;
import com.nnamdi.gpi.dronemanagementappwebflux.request.RegisterDroneDto;
import com.nnamdi.gpi.dronemanagementappwebflux.service.impl.DroneServiceImpl;
import com.nnamdi.gpi.dronemanagementappwebflux.util.DroneUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.nnamdi.gpi.dronemanagementappwebflux.mock.TestMock.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class DroneServiceTest {
    @Mock
    DroneUtil droneUtil;

    @Mock
    ModelMapper modelMapper;

    @Mock
    DroneRepository droneRepository;
    @Mock
    MessageSource messageSource;

    @Mock
    MessageProvider messageProvider;

    @Mock
    DroneService droneService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        modelMapper = new ModelMapper();
        messageProvider = new MessageProvider(messageSource);

        droneService = new DroneServiceImpl(modelMapper, droneUtil, droneRepository, messageProvider);
    }

    @Test
    void testRegisterDrone() {
        RegisterDroneDto droneDto = registerDroneDto();
        Drone drone = buildDrone(droneDto);
        when(droneRepository.save(any(Drone.class))).thenReturn(Mono.just(drone));
        when(droneRepository.findByCoordinatesOrName(droneDto.getCoordinateX(), droneDto.getCoordinateY(), droneDto.getName())).thenReturn(Mono.empty());
        when(droneUtil.buildDroneEntity(droneDto)).thenReturn(drone);
        StepVerifier.create(droneService.registerDrone(droneDto))
                .expectNext(buildDroneDto()).verifyComplete();
    }
}
