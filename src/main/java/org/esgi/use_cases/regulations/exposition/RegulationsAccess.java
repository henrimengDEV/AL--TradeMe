package org.esgi.use_cases.regulations.exposition;

import org.esgi.shared_kernel.cqs.CommandBus;
import org.esgi.shared_kernel.cqs.QueryBus;

public class RegulationsAccess {
    public final CommandBus commandBus;
    public final QueryBus   queryBus;

    public RegulationsAccess(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }
}
