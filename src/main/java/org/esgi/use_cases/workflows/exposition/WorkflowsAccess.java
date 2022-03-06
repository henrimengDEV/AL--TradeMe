package org.esgi.use_cases.workflows.exposition;

import org.esgi.kernel.cqs.CommandBus;
import org.esgi.kernel.cqs.QueryBus;

public class WorkflowsAccess {

    public final  CommandBus commandBus;
    public final QueryBus   queryBus;

    public WorkflowsAccess(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }
}
