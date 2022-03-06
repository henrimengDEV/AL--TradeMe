package org.esgi.controllers;


import org.esgi.shared_kernel.exceptions.NoSuchEntityException;
import org.esgi.shared_kernel.exceptions.NotValidAttributeException;
import org.esgi.shared_kernel.exceptions.PaymentException;
import org.esgi.shared_kernel.exceptions.RegulatedException;

import javax.json.Json;
import javax.validation.ValidationException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.net.URI;

@Provider
public class ErrorsMapper implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException exception) {
        int code = 500;
        String location = "";

        if (exception instanceof WebApplicationException)
            code = ((WebApplicationException) exception).getResponse().getStatus();

        else if (exception instanceof NotValidAttributeException)
            code = Response.Status.NOT_ACCEPTABLE.getStatusCode();

        else if (exception instanceof NoSuchEntityException)
            code = Response.Status.NOT_FOUND.getStatusCode();

        else if (exception instanceof ValidationException)
            code = Response.Status.NOT_ACCEPTABLE.getStatusCode();

        else if (exception instanceof PaymentException)
            code = 424;

        else if(exception instanceof IllegalArgumentException)
            code = Response.Status.BAD_REQUEST.getStatusCode();

        else if(exception instanceof RegulatedException){
            code = Response.Status.EXPECTATION_FAILED.getStatusCode();
            location = URI.create("/error/" + ((RegulatedException) exception).getRegulationId()).toString();
        }

        else exception.printStackTrace();

        return Response.status(code)
                .header("Location", location)
                       .entity(Json.createObjectBuilder()
                                   .add("error", exception.getMessage())
                                   .add("code", String.valueOf(code))
                                   .build()
                       )
                       .build();
    }

}
