package com.nnamdi.gpi.dronemanagementappwebflux.model;

import com.nnamdi.gpi.dronemanagementappwebflux.util.Direction;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "drones")
public class Drone implements Serializable {

    @Column(value = "coordinatex")
    private int coordinateX;

    private String name;

    @Column(value = "coordinatey")
    private int coordinateY;

    private Direction direction;

    @Column(value = "id")
    private String id;

    @LastModifiedDate
    @Column(value = "updated_date")
    private ZonedDateTime lastModifiedDate;


    @CreatedDate
    @Column(value = "created_date")
    private ZonedDateTime createdDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drone drone = (Drone) o;
        return coordinateX == drone.coordinateX && coordinateY == drone.coordinateY && Objects.equals(name, drone.name) && direction == drone.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinateX, name, coordinateY, direction);
    }
}
