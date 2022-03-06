package org.esgi.core.use_cases.projects.application.query;

import org.esgi.kernel.cqs.QueryHandler;
import org.esgi.core.use_cases.member.domain.model.MemberId;
import org.esgi.core.use_cases.projects.domain.Project;
import org.esgi.core.use_cases.projects.domain.ProjectsRepository;
import org.esgi.controllers.market.response.ProjectsResponse;

import java.util.List;

public class RetrieveProjectsByMemberIdHandler implements QueryHandler<RetrieveProjectsByMemberId, ProjectsResponse> {
    private final ProjectsRepository projectsRepository;

    public RetrieveProjectsByMemberIdHandler(ProjectsRepository projectsRepository) {this.projectsRepository = projectsRepository;}

    @Override
    public ProjectsResponse handle(RetrieveProjectsByMemberId query) {
        List<Project> projects = projectsRepository.findByMemberId(MemberId.of(query.id));
        return null;
    }
}
