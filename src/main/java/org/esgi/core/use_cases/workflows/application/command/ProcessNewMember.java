package org.esgi.core.use_cases.workflows.application.command;

import org.esgi.kernel.cqs.Command;

public class ProcessNewMember implements Command {
    public final Integer memberId;
    public final Integer paymentId;
    public final String memberMail;
    public final String memberName;
    public final String paymentAmount;
    public final String memberlogin;

    public ProcessNewMember(Integer memberId,
                            Integer paymentId,
                            String memberMail,
                            String memberName,
                            String paymentAmount,
                            String memberlogin) {
        this.memberId = memberId;
        this.paymentId = paymentId;
        this.memberMail = memberMail;
        this.memberName = memberName;
        this.paymentAmount = paymentAmount;
        this.memberlogin = memberlogin;
    }
}
