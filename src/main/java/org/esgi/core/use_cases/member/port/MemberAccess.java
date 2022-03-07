package org.esgi.core.use_cases.member.port;

import org.esgi.controllers.membership.response.MemberResponseAdapter;
import org.esgi.kernel.cqs.CommandBus;
import org.esgi.kernel.cqs.QueryBus;

public class MemberAccess {

  public final CommandBus            commandBus;
  public final QueryBus              queryBus;
  public final MemberResponseAdapter memberResponseAdapter;


  public MemberAccess(CommandBus commandBus, QueryBus queryBus,
                      MemberResponseAdapter memberResponseAdapter) {
    this.commandBus = commandBus;
    this.queryBus = queryBus;
    this.memberResponseAdapter = memberResponseAdapter;
  }

}
