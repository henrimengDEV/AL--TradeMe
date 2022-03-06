package org.esgi.use_cases.projects;

import org.esgi.ApplicationConfiguration;
import org.esgi.shared_kernel.annotations.Configuration;
import org.esgi.shared_kernel.cqs.*;
import org.esgi.use_cases.projects.application.query.RetrieveProjectById;
import org.esgi.use_cases.projects.application.query.RetrieveProjectByIdHandler;
import org.esgi.use_cases.projects.application.query.RetrieveProjectsByMemberId;
import org.esgi.use_cases.projects.application.query.RetrieveProjectsByMemberIdHandler;
import org.esgi.use_cases.projects.domain.ProjectsRepository;
import org.esgi.use_cases.projects.infrastructure.InMemoryProjectsRepository;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Dependent
public class ProjectsConfiguration {
    private final ApplicationConfiguration appConfiguration;

    public ProjectsConfiguration(ApplicationConfiguration appConfiguration) {this.appConfiguration = appConfiguration;}

    //Command bus
    @RequestScoped
    public CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
        return new SimpleCommandBus(commandHandlerMap);
    }

    //Query bus
    @RequestScoped
    public QueryBus queryBus() {
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap = new HashMap<>();
        queryHandlerMap.put(RetrieveProjectById.class, new RetrieveProjectByIdHandler(projectsRepository()));
        queryHandlerMap.put(RetrieveProjectsByMemberId.class, new RetrieveProjectsByMemberIdHandler(projectsRepository()));
        return new SimpleQueryBus(queryHandlerMap);
    }


    //Repository
    private ProjectsRepository projectsRepository() {
        return InMemoryProjectsRepository.getInstance();
    }

}
