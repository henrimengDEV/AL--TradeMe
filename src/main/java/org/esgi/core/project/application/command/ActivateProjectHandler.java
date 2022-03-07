package org.esgi.core.project.application.command;


import org.esgi.core.project.domain.ProjectsRepository;
import org.esgi.core.project.domain.project.ProjectId;
import org.esgi.core.project.domain.project.Project;
import org.esgi.kernel.annotations.Service;
import org.esgi.kernel.cqs.CommandHandler;

@Service
public class ActivateProjectHandler implements CommandHandler<ActivateProject, ProjectId> {

  private final ProjectsRepository projectsRepository;


  public ActivateProjectHandler(ProjectsRepository projectsRepository) {
    this.projectsRepository = projectsRepository;
  }

  @Override
  public ProjectId handle(ActivateProject command) {
    Project project = projectsRepository.findById(ProjectId.of(command.projectId));
    project.activateProject();
    return projectsRepository.add(project).getProjectId();
  }
}
