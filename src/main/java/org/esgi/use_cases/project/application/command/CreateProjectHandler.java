package org.esgi.use_cases.project.application.command;

import org.esgi.kernel.CommandHandler;
import org.esgi.use_cases.project.domain.Project;
import org.esgi.use_cases.project.domain.ProjectId;
import org.esgi.use_cases.project.domain.Projects;

public class CreateProjectHandler implements CommandHandler<CreateProject, ProjectId> {

    private final Projects projects;

    public CreateProjectHandler(Projects projects) {
        this.projects = projects;
    }

    @Override
    public ProjectId handle(CreateProject createProject) {
        final ProjectId projectId = projects.nextIdentity();
        Project project = Project.of(projectId, createProject.getName(), createProject.getAddress(), createProject.getContractorId(), createProject.getMemberId());
        return projectId;
    }
}
