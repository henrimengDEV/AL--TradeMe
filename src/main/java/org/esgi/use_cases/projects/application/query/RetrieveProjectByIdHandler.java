package org.esgi.use_cases.projects.application.query;

import org.esgi.shared_kernel.cqs.QueryHandler;
import org.esgi.use_cases.projects.domain.Project;
import org.esgi.use_cases.projects.domain.ProjectId;
import org.esgi.use_cases.projects.domain.ProjectsRepository;
import org.esgi.use_cases.projects.port.response.ProjectsResponse;

public class RetrieveProjectByIdHandler implements QueryHandler<RetrieveProjectById, ProjectsResponse> {
    private final ProjectsRepository projectsRepository;

    public RetrieveProjectByIdHandler(ProjectsRepository projectsRepository) {this.projectsRepository = projectsRepository;}

    @Override
    public ProjectsResponse handle(RetrieveProjectById query) {
        Project project = projectsRepository.findById(ProjectId.of(query.id));
        return null;
    }
}
