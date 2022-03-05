package org.esgi.use_cases.member.exposition;

import org.esgi.kernel.cqs.CommandBus;
import org.esgi.kernel.cqs.QueryBus;
import org.esgi.use_cases.member.exposition.response.MemberResponseAdapter;

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
