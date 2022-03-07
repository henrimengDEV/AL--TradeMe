package org.esgi.core.project.domain;

import java.util.List;
import org.esgi.core.member.domain.model.MemberId;
import org.esgi.core.project.domain.project.Project;
import org.esgi.core.project.domain.project.ProjectId;

public interface ProjectsRepository {

  Project add(Project project);

  Project update(Project project);

  ProjectId nextId();

  List<Project> findAll();

  Project findById(ProjectId projectId);

  List<Project> findByMemberId(MemberId memberId);
}
