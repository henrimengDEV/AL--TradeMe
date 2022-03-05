package org.esgi.use_cases.project.application.query;

import org.esgi.kernel.QueryHandler;

public class RetrieveProjectsHandler implements QueryHandler<RetrieveProjects, Void> {
    
    @Override
    public Void handle(RetrieveProjects command) {
        return null;
    }
}
