package com.nnamdi.gpi.dronemanagementappwebflux.util;


import com.nnamdi.gpi.dronemanagementappwebflux.dto.Error;
import com.nnamdi.gpi.dronemanagementappwebflux.dto.Response;
import com.nnamdi.gpi.dronemanagementappwebflux.dto.ResponseCodes;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtil {
    public <T> Response<T> getSuccessResponse(T data) {
        return new Response<>(ResponseCodes.SUCCESS.code(), ConstantsUtil.SUCCESSFUL, data, null);
    }

    public <T> Response<T> getErrorResponse(Error err) {
        return new Response<>(err.getCode(), err.getMessage(), null, err.getDescriptiveMessage());
    }
}
