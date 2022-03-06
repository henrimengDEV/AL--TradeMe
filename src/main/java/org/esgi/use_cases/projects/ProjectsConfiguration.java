package org.esgi.use_cases.projects;

import org.esgi.ApplicationConfiguration;
import org.esgi.shared_kernel.annotations.Configuration;
import org.esgi.shared_kernel.cqs.*;
import org.esgi.use_cases.projects.domain.ProjectsRepository;
import org.esgi.use_cases.projects.infrastructure.InMemoryProjectsRepository;

import javax.enterprise.context.Dependent;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Dependent
public class ProjectsConfiguration {
    private  ApplicationConfiguration appConfiguration;

    //Command bus
    @Singleton
    public CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
        return new SimpleCommandBus(commandHandlerMap);
    }

    //Query bus
    @Singleton
    public QueryBus queryBus() {
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap = new HashMap<>();
        return new SimpleQueryBus(queryHandlerMap);
    }


    //Repository
    private ProjectsRepository projectsRepository() {
        return InMemoryProjectsRepository.getInstance();
    }

}
