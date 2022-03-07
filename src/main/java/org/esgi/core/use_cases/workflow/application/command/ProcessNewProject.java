package org.esgi.core.use_cases.workflow.application.command;

import org.esgi.kernel.cqs.Command;

public class ProcessNewProject implements Command {

  public final Integer projectId;
  public final Integer contractorId;
  public final String  contractorMail;

  public ProcessNewProject(Integer projectId,
                           Integer contractorId,
                           String contractorMail) {
    this.projectId = projectId;
    this.contractorId = contractorId;
    this.contractorMail = contractorMail;

  }
}
