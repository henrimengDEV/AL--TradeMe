package org.esgi.core.project.application.query;

import org.esgi.kernel.cqs.Query;

public class RetrieveProjectById implements Query {

  public final int id;

  public RetrieveProjectById(int id) {
    this.id = id;
  }
}
