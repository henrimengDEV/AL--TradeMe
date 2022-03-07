package org.esgi.core.use_cases.regulation.domain;

import java.util.Objects;
import org.esgi.core.use_cases.project.domain.project.ProjectId;

public class ProjectRegulation implements Regulation {

  private final RegulationId regulationId;
  private final String       description;
  private final ProjectId    project;

  private ProjectRegulation(RegulationId regulationId,
                            String description,
                            ProjectId projectId) {
    this.regulationId = regulationId;
    this.description = description;
    this.project = projectId;
  }

  public static ProjectRegulation of(RegulationId regulationId,
                                     String description,
                                     ProjectId projectId) {
    return new ProjectRegulation(regulationId, description, projectId);
  }

  @Override
  public RegulationId getRegulationId() {
    return regulationId;
  }

  public String getDescription() {
    return description;
  }

  public ProjectId getProject() {
    return project;
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }
      if (o == null || getClass() != o.getClass()) {
          return false;
      }
    ProjectRegulation that = (ProjectRegulation) o;
    return Objects.equals(regulationId, that.regulationId) && Objects.equals(description,
                                                                             that.description)
        && Objects.equals(project, that.project);
  }

  @Override
  public int hashCode() {
    return Objects.hash(regulationId, description, project);
  }

  @Override
  public String toString() {
    return "ProjectRegulation{" +
        "regulationId=" + regulationId +
        ", description='" + description + '\'' +
        ", pro=" + project +
        '}';
  }
}
