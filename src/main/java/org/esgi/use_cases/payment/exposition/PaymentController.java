package org.esgi.use_cases.payment.exposition;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/payment")
public final class PaymentController {

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public String welcome() {
        return "Welcome to Payment";
    }
}
