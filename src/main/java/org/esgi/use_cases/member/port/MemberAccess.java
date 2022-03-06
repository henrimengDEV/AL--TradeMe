package org.esgi.use_cases.member.port;

import org.esgi.shared_kernel.cqs.CommandBus;
import org.esgi.shared_kernel.cqs.QueryBus;
import org.esgi.use_cases.member.port.response.MemberResponseAdapter;

public class MemberAccess {

    public final CommandBus            commandBus;
    public final QueryBus              queryBus;
    public final MemberResponseAdapter memberResponseAdapter;


    public MemberAccess(CommandBus commandBus, QueryBus queryBus, MemberResponseAdapter memberResponseAdapter) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
        this.memberResponseAdapter = memberResponseAdapter;
    }

}
