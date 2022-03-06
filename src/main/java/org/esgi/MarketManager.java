package org.esgi;

import org.esgi.kernel.annotations.Controller;
import org.esgi.use_cases.member.exposition.request.MemberRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.logging.Logger;

@Controller
@ApplicationScoped
@Path("/market")
public class MarketManager {
    private static final Logger LOGGER = Logger.getLogger(MembershipManager.class.getName());

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response requestTradesman(@Valid MemberRequest request) {
        LOGGER.info("Registering Member" + "\n");


        return Response.created(URI.create("/membership/" )).build();
    }
}
