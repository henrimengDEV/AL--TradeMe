package org.esgi.use_cases.regulation.exposition;

import org.esgi.kernel.CommandBus;
import org.esgi.kernel.QueryBus;
import org.esgi.use_cases.regulation.application.command.CreateRegulationCommand;
import org.esgi.use_cases.regulation.application.query.RetrieveRegulationByIdQuery;
import org.esgi.use_cases.regulation.application.query.RetrieveRegulationsQuery;
import org.esgi.use_cases.regulation.domain.Regulation;
import org.esgi.use_cases.regulation.exposition.request.CreateRegulationRequest;
import org.esgi.use_cases.regulation.exposition.request.RetrieveRegulationByIdRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
@Path("/regulation")
public final class RegulationController {

    private final Logger log = Logger.getLogger(this.getClass().getName());

    @Inject
    CommandBus commandBus;

    @Inject
    QueryBus queryBus;

    @GET
    @Path("/")
    public Response getAll() {
        this.log.info("[regulation] getAll");
        RetrieveRegulationsQuery query = new RetrieveRegulationsQuery();
        List<Regulation> regulations = this.queryBus.send(query);
        return Response.ok(regulations).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") int id) {
        this.log.info("[regulation] getById");
        RetrieveRegulationByIdQuery query = new RetrieveRegulationByIdQuery(id);
        Regulation regulation = this.queryBus.send(query);
        return Response.ok(regulation).build();
    }

    @POST
    @Path("/")
    public Response create(@Valid CreateRegulationRequest request) {
        this.log.info("[regulation] create");

        CreateRegulationCommand command = CreateRegulationCommand.of()
                .id(request.id)
                .creationDate(request.creationDate);

        int regulationId = this.commandBus.send(command);
        return Response.created(URI.create("/regulation/" + regulationId)).build();
    }
}
