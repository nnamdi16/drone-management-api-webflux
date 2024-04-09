package com.nnamdi.gpi.dronemanagementappwebflux.dto;

public record Response(Integer code, String message, Object data, String descriptiveMessage) {
}
