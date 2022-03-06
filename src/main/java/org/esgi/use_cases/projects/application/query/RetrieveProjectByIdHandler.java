package org.esgi.use_cases.projects.application.query;

import org.esgi.shared_kernel.cqs.QueryHandler;
import org.esgi.use_cases.projects.domain.Project;
import org.esgi.use_cases.projects.domain.ProjectId;
import org.esgi.use_cases.projects.domain.ProjectsRepository;

public class RetrieveProjectByIdHandler implements QueryHandler<RetrieveProjectById, Project> {
    private final ProjectsRepository projectsRepository;

    public RetrieveProjectByIdHandler(ProjectsRepository projectsRepository) {this.projectsRepository = projectsRepository;}

    @Override
    public Project handle(RetrieveProjectById query) {
        return projectsRepository.findById(ProjectId.of(query.id));
    }
}
