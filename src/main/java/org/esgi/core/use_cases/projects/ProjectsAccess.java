package org.esgi.core.use_cases.projects;

import org.esgi.kernel.cqs.CommandBus;
import org.esgi.kernel.cqs.QueryBus;

public class ProjectsAccess {
    public final CommandBus commandBus;
    public final QueryBus queryBus;

    public ProjectsAccess(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }
}
