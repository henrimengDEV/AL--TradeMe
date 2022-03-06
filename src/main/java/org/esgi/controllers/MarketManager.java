package org.esgi.controllers;

import org.esgi.engines.RegulationsEngine;
import org.esgi.shared_kernel.annotations.Controller;
import org.esgi.use_cases.member.port.MemberAccess;
import org.esgi.use_cases.projects.application.query.RetrieveProjectsByMemberId;
import org.esgi.use_cases.projects.port.ProjectsAccess;
import org.esgi.use_cases.projects.port.response.ProjectsResponse;
import org.esgi.use_cases.workflows.exposition.WorkflowsAccess;

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
public class MarketManager {
    private static final Logger LOGGER = Logger.getLogger(MarketManager.class.getName());

    private final WorkflowsAccess   workflowsAccess;
    private final ProjectsAccess    projectsAccess;
    private final MemberAccess      memberAccess;
    private final RegulationsEngine regulationsEngine;

    public MarketManager(WorkflowsAccess workflowsAccess,
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

        ProjectsResponse projectsOfMemberResponse = projectsAccess.queryBus.send(new RetrieveProjectsByMemberId(memberId));

        regulationsEngine.evaluateRequestTradesman(projectsOfMemberResponse, projectId, memberId);



        return Response.created(URI.create("/member/"+memberId)).build();
    }
}
