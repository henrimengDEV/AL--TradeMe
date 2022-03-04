package org.esgi.use_cases.member.exposition;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/member")
public final class MemberController {

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public String welcome() {
        return "Welcome to Member";
    }
}
