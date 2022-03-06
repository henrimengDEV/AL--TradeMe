package org.esgi.core.use_cases.regulations.application.command;

import org.esgi.kernel.cqs.Command;

public class RegulateUnsubscribedTradesman implements Command {
    public final Integer memberId;
    public final String  description;
    public final String  memberLogin;
    public final String  memberMail;

    public RegulateUnsubscribedTradesman(Integer memberId,
                                         String description,
                                         String memberLogin,
                                         String memberMail) {
        this.memberId = memberId;
        this.description = description;
        this.memberLogin = memberLogin;
        this.memberMail = memberMail;
    }
}
