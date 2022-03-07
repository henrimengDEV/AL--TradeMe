package org.esgi.core.regulation.application.command;

import org.esgi.kernel.cqs.Command;

public class CreateMemberRegulation implements Command {

  public final Integer memberId;
  public final String  description;

  public CreateMemberRegulation(Integer memberId, String description) {
    this.memberId = memberId;
    this.description = description;
  }
}
