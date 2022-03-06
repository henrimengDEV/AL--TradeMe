package org.esgi.use_cases.projects.port;

import org.esgi.shared_kernel.cqs.CommandBus;
import org.esgi.shared_kernel.cqs.QueryBus;

public class ProjectsAccess {
    public final CommandBus commandBus;
    public final QueryBus queryBus;

    public ProjectsAccess(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }
}
