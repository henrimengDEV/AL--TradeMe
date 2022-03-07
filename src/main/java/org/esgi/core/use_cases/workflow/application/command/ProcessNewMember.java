package org.esgi.core.use_cases.workflow.application.command;

import org.esgi.kernel.cqs.Command;

public class ProcessNewMember implements Command {

  public final Integer memberId;
  public final Integer paymentId;
  public final String  memberMail;
  public final String  paymentAmount;

  public ProcessNewMember(Integer memberId,
                          Integer paymentId,
                          String memberMail,
                          String paymentAmount) {
    this.memberId = memberId;
    this.paymentId = paymentId;
    this.memberMail = memberMail;
    this.paymentAmount = paymentAmount;
  }
}
