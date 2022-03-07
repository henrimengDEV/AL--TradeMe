package org.esgi.controllers.market.response;

import java.util.List;

public class ProjectsResponse {

  public final List<ProjectResponse> projectResponses;

  public ProjectsResponse(List<ProjectResponse> projectResponses) {
    this.projectResponses = projectResponses;
  }
}
