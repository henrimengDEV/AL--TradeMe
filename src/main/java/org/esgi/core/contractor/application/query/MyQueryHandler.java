package org.esgi.core.contractor.application.query;

import org.esgi.kernel.cqs.QueryHandler;

public class MyQueryHandler implements QueryHandler<MyQuery, Void> {

  @Override
  public Void handle(MyQuery command) {
    return null;
  }
}
