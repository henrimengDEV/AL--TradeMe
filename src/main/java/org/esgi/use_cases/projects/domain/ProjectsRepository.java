package org.esgi.use_cases.projects.domain;

import java.util.List;

public interface ProjectsRepository {

    Project add(Project project);

    Project update(Project project);

    ProjectId nextId();

    List<Project> findAll();

    Project findById(ProjectId projectId);
}
