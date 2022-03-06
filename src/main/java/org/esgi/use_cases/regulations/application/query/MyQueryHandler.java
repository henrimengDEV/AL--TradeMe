package org.esgi.use_cases.regulations.application.query;

import org.esgi.shared_kernel.cqs.QueryHandler;

public class MyQueryHandler implements QueryHandler<MyQuery, Void> {
    
    @Override
    public Void handle(MyQuery command) {
        return null;
    }
}
