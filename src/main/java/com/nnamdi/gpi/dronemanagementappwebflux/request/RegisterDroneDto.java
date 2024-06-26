package com.nnamdi.gpi.dronemanagementappwebflux.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.nnamdi.gpi.dronemanagementappwebflux.util.Direction;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class RegisterDroneDto {
    @NotNull(message = "x-coordinate must be provided")
    @JsonProperty("x-coordinate")
    @Max(value = 9, message = "x co-ordinate field boundary must not exceed 10")
    @Min(value = 0, message = "x co-ordinate field boundary must not be below 0")
    private int coordinateX;

    @NotNull(message = "y-coordinate must be provided")
    @Max(value = 9, message = "y co-ordinate field boundary must not exceed 10")
    @Min(value = 0, message = "y co-ordinate field boundary must not be below 0")
    @JsonProperty("y-coordinate")
    private int coordinateY;

    @NotNull(message = "direction must be provided")
    private Direction direction;

    @NotBlank(message = "name of drone must be provided")
    private String name;

}
