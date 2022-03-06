package org.esgi.core.use_cases.projects.application.query;

import org.esgi.kernel.cqs.QueryHandler;
import org.esgi.core.use_cases.projects.domain.Project;
import org.esgi.core.use_cases.projects.domain.ProjectId;
import org.esgi.core.use_cases.projects.domain.ProjectsRepository;
import org.esgi.controllers.market.response.ProjectsResponse;

public class RetrieveProjectByIdHandler implements QueryHandler<RetrieveProjectById, ProjectsResponse> {
    private final ProjectsRepository projectsRepository;

    public RetrieveProjectByIdHandler(ProjectsRepository projectsRepository) {this.projectsRepository = projectsRepository;}

    @Override
    public ProjectsResponse handle(RetrieveProjectById query) {
        Project project = projectsRepository.findById(ProjectId.of(query.id));
        return null;
    }
}
