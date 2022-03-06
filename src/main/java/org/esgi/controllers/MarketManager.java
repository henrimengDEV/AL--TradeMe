package org.esgi.controllers;

import org.esgi.shared_kernel.annotations.Controller;
import org.esgi.use_cases.member.application.query.RetrieveMemberById;
import org.esgi.use_cases.member.domain.model.Member;
import org.esgi.use_cases.member.port.MemberAccess;
import org.esgi.use_cases.projects.application.query.RetrieveProjectById;
import org.esgi.use_cases.projects.domain.Project;
import org.esgi.use_cases.projects.exposition.ProjectsAccess;
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
    private static final Logger LOGGER = Logger.getLogger(MembershipManager.class.getName());

    private final WorkflowsAccess workflowsAccess;
    private final ProjectsAccess  projectsAccess;
    private final MemberAccess    memberAccess;

    public MarketManager(WorkflowsAccess workflowsAccess,
                         ProjectsAccess projectsAccess,
                         MemberAccess memberAccess) {
        this.workflowsAccess = workflowsAccess;
        this.projectsAccess = projectsAccess;
        this.memberAccess = memberAccess;
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/request/tradesman")
    public Response requestTradesman(@Valid @NotNull @QueryParam("memberId") Integer memberId,
                                     @Valid @NotNull @QueryParam("projectId") Integer projectId) {
        LOGGER.info("Requesting Tradesman" + "\n");

        Member  member  = memberAccess.queryBus.send(new RetrieveMemberById(memberId));
        Project project = projectsAccess.queryBus.send(new RetrieveProjectById(projectId));

        //Regulation engine check if this member can be requested for this project


        return Response.created(URI.create("/market/")).build();
    }
}
