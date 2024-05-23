package com.nnamdi.gpi.dronemanagementappwebflux.dto;

public record Response<T>(Integer code, String message, T data, String descriptiveMessage) {
}
