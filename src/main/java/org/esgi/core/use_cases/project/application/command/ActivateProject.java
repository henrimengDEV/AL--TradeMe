package org.esgi.core.use_cases.project.application.command;

import org.esgi.kernel.cqs.Command;

/**
 * Command object
 */
@SuppressWarnings("all")
public final class ActivateProject implements Command {

  public final Integer projectId;

  public ActivateProject(Integer projectId) {
    this.projectId = projectId;
  }
}
