package org.esgi.core.use_cases.project.application.query;

import org.esgi.core.use_cases.project.domain.ProjectsRepository;
import org.esgi.core.use_cases.project.domain.project.Project;
import org.esgi.core.use_cases.project.domain.project.ProjectId;
import org.esgi.kernel.cqs.QueryHandler;

public class RetrieveProjectByIdHandler implements QueryHandler<RetrieveProjectById, Project> {

  private final ProjectsRepository projectsRepository;

  public RetrieveProjectByIdHandler(ProjectsRepository projectsRepository) {
    this.projectsRepository = projectsRepository;
  }

  @Override
  public Project handle(RetrieveProjectById query) {
    return projectsRepository.findById(ProjectId.of(query.id));
  }
}
