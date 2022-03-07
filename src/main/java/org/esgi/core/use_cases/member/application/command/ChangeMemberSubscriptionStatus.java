package org.esgi.core.use_cases.member.application.command;

import org.esgi.kernel.cqs.Command;

public class ChangeMemberSubscriptionStatus implements Command {

  public Integer memberId;
  public boolean isSubscribed;

  public ChangeMemberSubscriptionStatus(Integer memberId, boolean isSubscribed) {
    this.memberId = memberId;
    this.isSubscribed = isSubscribed;
  }
}
