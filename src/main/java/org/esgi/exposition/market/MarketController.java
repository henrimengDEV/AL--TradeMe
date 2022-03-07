package org.esgi.exposition.market;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.esgi.exposition.market.request.CreateProjectRequest;
import org.esgi.infrastructure.RegulationEngine;
import org.esgi.core.member.application.AddressDTO;
import org.esgi.core.member.application.query.RetrieveMemberById;
import org.esgi.core.member.domain.model.Member;
import org.esgi.core.project.application.DailyRemunerationDTO;
import org.esgi.core.project.application.JobDTO;
import org.esgi.core.project.application.command.ActivateProject;
import org.esgi.core.project.application.command.CreateProject;
import org.esgi.core.project.application.query.RetrieveProjectById;
import org.esgi.core.project.application.query.RetrieveProjectsByMemberId;
import org.esgi.core.project.domain.project.Project;
import org.esgi.core.project.domain.project.ProjectId;
import org.esgi.kernel.annotations.Controller;
import org.esgi.kernel.cqs.CommandBus;
import org.esgi.kernel.cqs.QueryBus;

@Controller
@ApplicationScoped
@Path("/market")
public class MarketController {

  private static final Logger LOGGER = Logger.getLogger(MarketController.class.getName());


  private final CommandBus       commandBus;
  private final QueryBus         queryBus;
  private final RegulationEngine regulationsEngine;

  public MarketController(CommandBus commandBus, QueryBus queryBus, RegulationEngine regulationsEngine) {
    this.commandBus = commandBus;
    this.queryBus = queryBus;
    this.regulationsEngine = regulationsEngine;
  }

  @POST
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/project")
  public Response createProject(@Valid CreateProjectRequest createProjectRequest) {
    LOGGER.info("creating Project" + "\n");

    CreateProject createProject = new CreateProject(createProjectRequest.contractorId, createProjectRequest.name,
                                                    createProjectRequest.startDate, createProjectRequest.endDate,
                                                    createProjectRequest.requiredJobs.stream().map(
                                                                            job -> new JobDTO(job.jobName,
                                                                                              job.isEducationCertificateRequired))
                                                                                     .collect(Collectors.toList()),
                                                    new DailyRemunerationDTO(createProjectRequest.dailyRemuneration,
                                                                             createProjectRequest.devise),
                                                    new AddressDTO(createProjectRequest.address.city,
                                                                   createProjectRequest.address.street,
                                                                   createProjectRequest.address.country,
                                                                   createProjectRequest.address.zipcode)

    );
    System.out.println("created");
    ProjectId projectId = commandBus.send(createProject);

    System.out.println("command");
    ActivateProject activateProject = new ActivateProject(projectId.getValue());
    projectId = commandBus.send(activateProject);

    return Response.created(URI.create("/project/" + projectId.getValue())).build();
  }

  @POST
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/request/tradesman")
  public Response requestTradesman(@Valid @QueryParam("memberId") @NotNull Integer memberId,
                                   @Valid @QueryParam("projectId") @NotNull Integer projectId) {
    LOGGER.info("Requesting Tradesman" + "\n");

    Member member = queryBus.send(new RetrieveMemberById(memberId));
    regulationsEngine.auditTradesman(member);

    Project project = queryBus.send(new RetrieveProjectById(projectId));
    regulationsEngine.auditProject(project);

    return Response.created(URI.create("/member/" + memberId)).build();
  }

  @POST
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/match/tradesman")
  public Response matchTradesman(@Valid @QueryParam("memberId") @NotNull Integer memberId,
                                 @Valid @QueryParam("projectId") @NotNull Integer projectId,
                                 @Valid @QueryParam("contractorId") @NotNull Integer contractorId) {
    LOGGER.info("Matching Tradesman" + "\n");

    Project project = queryBus.send(new RetrieveProjectById(projectId));
    regulationsEngine.auditProject(project);

    List<Project> projectsOfMember = queryBus.send(new RetrieveProjectsByMemberId(memberId));

    return Response.created(URI.create("/member/" + memberId)).build();
  }
}
