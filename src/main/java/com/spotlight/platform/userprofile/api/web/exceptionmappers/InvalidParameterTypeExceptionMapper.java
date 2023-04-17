package com.spotlight.platform.userprofile.api.web.exceptionmappers;

import com.spotlight.platform.userprofile.api.core.exceptions.InvalidParameterTypeException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidParameterTypeExceptionMapper implements ExceptionMapper<InvalidParameterTypeException> {
    @Override
    public Response toResponse(InvalidParameterTypeException exception) {
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
