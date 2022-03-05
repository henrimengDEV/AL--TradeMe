package org.esgi.use_cases.project.exposition;

import org.esgi.use_cases.project.application.command.CreateProject;
import org.esgi.use_cases.project.application.command.CreateProjectHandler;
import org.esgi.use_cases.project.domain.*;
import org.esgi.use_cases.project.exposition.request.CreateProjectRequest;
import org.esgi.use_cases.project.infrastructure.InMemoryProjects;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/project")
public final class ProjectController {

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public String welcome() {
        return "Welcome to Project";
    }

    @GET
    @Path("/")
    public Response retrieveAll() {



        return Response.ok().build();
    }

    @POST
    @Path("/test")
    public Response createProject(@Valid CreateProjectRequest createProjectRequest) {
        CreateProject createProject = CreateProject.of(createProjectRequest.name,
                ProjectAddress.of(createProjectRequest.address),
                ProjectContractorId.of(createProjectRequest.contractorId),
                ProjectMemberId.of(createProjectRequest.memberId));

        CreateProjectHandler createProjectHandler = new CreateProjectHandler(new InMemoryProjects());
        ProjectId test = createProjectHandler.handle(createProject);
        return Response.ok(test).build();
    }

}
