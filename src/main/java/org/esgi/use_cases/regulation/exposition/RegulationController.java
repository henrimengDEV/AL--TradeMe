package org.esgi.use_cases.regulation.exposition;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/regulation")
public final class RegulationController {

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public String welcome() {
        return "Welcome to Regulation";
    }
}