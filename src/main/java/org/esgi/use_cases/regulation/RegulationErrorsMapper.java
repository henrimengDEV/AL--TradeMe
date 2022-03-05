package org.esgi.use_cases.regulation;

import org.esgi.kernel.exceptions.NoSuchEntityException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RegulationErrorsMapper implements ExceptionMapper<RuntimeException> {


    @Override
    public Response toResponse(RuntimeException exception) {
        int code = 500;

        if (exception instanceof NoSuchEntityException) code = Response.Status.NOT_FOUND.getStatusCode();

        return Response.status(code).entity(exception.getMessage()).build();
    }
}
