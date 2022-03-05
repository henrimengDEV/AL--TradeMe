package org.esgi.use_cases.project.domain;

import org.esgi.kernel.Repository;

import java.util.List;

@Repository
public interface Projects {
    ProjectId nextIdentity();
    Project findById(ProjectId id);
    List<Project> findAll();
    ProjectId add(Project Project);
    Void removeById(ProjectId id);
}
