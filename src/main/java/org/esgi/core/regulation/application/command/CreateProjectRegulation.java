package org.esgi.core.regulation.application.command;

import org.esgi.kernel.cqs.Command;

public class CreateProjectRegulation implements Command {

  public final Integer projectId;
  public final String  description;

  public CreateProjectRegulation(Integer projectId, String description) {
    this.projectId = projectId;
    this.description = description;
  }
}
