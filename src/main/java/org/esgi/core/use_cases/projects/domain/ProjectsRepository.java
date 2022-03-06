package org.esgi.core.use_cases.projects.domain;

import org.esgi.core.use_cases.member.domain.model.MemberId;

import java.util.List;

public interface ProjectsRepository {

    Project add(Project project);

    Project update(Project project);

    ProjectId nextId();

    List<Project> findAll();

    Project findById(ProjectId projectId);

    List<Project> findByMemberId(MemberId memberId);
}
