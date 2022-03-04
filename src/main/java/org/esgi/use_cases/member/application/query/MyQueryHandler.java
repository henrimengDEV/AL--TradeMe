package org.esgi.use_cases.member.application.query;

import org.esgi.kernel.QueryHandler;

public class MyQueryHandler implements QueryHandler<MyQuery, Void> {
    
    @Override
    public Void handle(MyQuery command) {
        return null;
    }
}
