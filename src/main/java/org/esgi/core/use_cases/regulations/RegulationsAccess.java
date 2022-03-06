package org.esgi.core.use_cases.regulations;

import org.esgi.kernel.cqs.CommandBus;
import org.esgi.kernel.cqs.QueryBus;

public class RegulationsAccess {
    public final CommandBus commandBus;
    public final QueryBus   queryBus;

    public RegulationsAccess(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }
}
