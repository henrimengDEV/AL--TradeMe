package org.esgi.core.use_cases.project.application.command;


import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;
import org.esgi.core.use_cases.contractor.domain.ContractorId;
import org.esgi.core.use_cases.member.domain.AddressFactory;
import org.esgi.core.use_cases.payment.domain.model.price.DollarPrice;
import org.esgi.core.use_cases.payment.domain.model.price.EuroPrice;
import org.esgi.core.use_cases.payment.domain.model.price.NoPrice;
import org.esgi.core.use_cases.project.domain.DailyRemuneration;
import org.esgi.core.use_cases.project.domain.ProjectsRepository;
import org.esgi.core.use_cases.project.domain.job.JobType;
import org.esgi.core.use_cases.project.domain.job.ProjectJob;
import org.esgi.core.use_cases.project.domain.project.DefaultProject;
import org.esgi.core.use_cases.project.domain.project.Project;
import org.esgi.core.use_cases.project.domain.project.ProjectId;
import org.esgi.kernel.annotations.Service;
import org.esgi.kernel.cqs.CommandHandler;

@Service
public class CreateProjectHandler implements CommandHandler<CreateProject, ProjectId> {

  private final ProjectsRepository projectsRepository;


  public CreateProjectHandler(ProjectsRepository projectsRepository) {
    this.projectsRepository = projectsRepository;
  }

  @Override
  public ProjectId handle(CreateProject command) {
    final ProjectId projectId = projectsRepository.nextId();

    //regulation of price check if null throw exception
    DailyRemuneration dailyRemuneration = DailyRemuneration.of(new NoPrice());
      if (command.dailyRemunerationDTO.devise.contains("eur")) {
          dailyRemuneration = DailyRemuneration.of(
              EuroPrice.of(command.dailyRemunerationDTO.value));
      }
      if (command.dailyRemunerationDTO.devise.contains("usd")) {
          dailyRemuneration = DailyRemuneration.of(
              DollarPrice.of(command.dailyRemunerationDTO.value));
      }

    //regulation check jobtype check if exist

    final List<ProjectJob> requiredJobs = command.requiredJobs.stream()
                                                              .map(job -> ProjectJob.of(
                                                                  JobType.fromString(job.jobName),
                                                                  true))
                                                              .collect(Collectors.toList());
    final Period period = Period.between(command.startDate, command.endDate);

    final Project project = DefaultProject.of(
        projectId,
        ContractorId.of(command.contractorId),
        command.name,
        period,
        requiredJobs,
        dailyRemuneration,
        AddressFactory.create(command.address.city, command.address.country, command.address.street,
                              command.address.zipCode)
    );

    return projectsRepository.add(project).getProjectId();
  }
}
