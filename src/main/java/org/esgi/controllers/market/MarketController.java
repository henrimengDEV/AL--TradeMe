package org.esgi.controllers.market;

import org.esgi.core.engines.RegulationsEngine;
import org.esgi.kernel.annotations.Controller;
import org.esgi.core.use_cases.member.MemberAccess;
import org.esgi.core.use_cases.projects.application.query.RetrieveProjectById;
import org.esgi.core.use_cases.projects.application.query.RetrieveProjectsByMemberId;
import org.esgi.core.use_cases.projects.ProjectsAccess;
import org.esgi.controllers.market.response.ProjectResponse;
import org.esgi.controllers.market.response.ProjectsResponse;
import org.esgi.core.use_cases.workflows.WorkflowsAccess;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.logging.Logger;

@Controller
@ApplicationScoped
@Path("/market")
public class MarketController {
    private static final Logger LOGGER = Logger.getLogger(MarketController.class.getName());

    private final WorkflowsAccess   workflowsAccess;
    private final ProjectsAccess    projectsAccess;
    private final MemberAccess      memberAccess;
    private final RegulationsEngine regulationsEngine;

    public MarketController(WorkflowsAccess workflowsAccess,
                            ProjectsAccess projectsAccess,
                            MemberAccess memberAccess,
                            RegulationsEngine regulationsEngine) {
        this.workflowsAccess = workflowsAccess;
        this.projectsAccess = projectsAccess;
        this.memberAccess = memberAccess;
        this.regulationsEngine = regulationsEngine;
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/request/tradesman")
    public Response requestTradesman(@Valid @NotNull @QueryParam("memberId") Integer memberId,
                                     @Valid @NotNull @QueryParam("projectId") Integer projectId) {
        LOGGER.info("Requesting Tradesman" + "\n");


        ProjectResponse projectResponse = projectsAccess.queryBus.send(new RetrieveProjectById(projectId));

        regulationsEngine.evaluateProject(projectResponse);


        return Response.created(URI.create("/member/" + memberId)).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/match/tradesman")
    public Response matchTradesman(@Valid @NotNull @QueryParam("memberId") Integer memberId,
                                   @Valid @NotNull @QueryParam("projectId") Integer projectId,
                                   @Valid @NotNull @QueryParam("contractorId") Integer contractorId) {
        LOGGER.info("Requesting Tradesman" + "\n");

        ProjectResponse  projectResponse          = projectsAccess.queryBus.send(new RetrieveProjectById(projectId));
        ProjectsResponse projectsOfMemberResponse = projectsAccess.queryBus.send(new RetrieveProjectsByMemberId(memberId));

        regulationsEngine.evaluateProject(projectResponse);


        return Response.created(URI.create("/member/" + memberId)).build();
    }
}
