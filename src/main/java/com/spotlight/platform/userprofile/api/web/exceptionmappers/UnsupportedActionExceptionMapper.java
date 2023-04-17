package com.spotlight.platform.userprofile.api.web.exceptionmappers;

import com.spotlight.platform.userprofile.api.core.exceptions.UnsupportedActionException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class UnsupportedActionExceptionMapper implements ExceptionMapper<UnsupportedActionException> {
    @Override
    public Response toResponse(UnsupportedActionException exception) {
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
