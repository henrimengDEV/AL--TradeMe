package org.esgi.core.use_cases.project.application.query;

import java.util.List;
import org.esgi.core.use_cases.member.domain.model.MemberId;
import org.esgi.core.use_cases.project.domain.ProjectsRepository;
import org.esgi.core.use_cases.project.domain.project.Project;
import org.esgi.kernel.cqs.QueryHandler;

public class RetrieveProjectsByMemberIdHandler implements
    QueryHandler<RetrieveProjectsByMemberId, List<Project>> {

  private final ProjectsRepository projectsRepository;

  public RetrieveProjectsByMemberIdHandler(ProjectsRepository projectsRepository) {
    this.projectsRepository = projectsRepository;
  }

  @Override
  public List<Project> handle(RetrieveProjectsByMemberId query) {
    return projectsRepository.findByMemberId(MemberId.of(query.id));
  }
}
