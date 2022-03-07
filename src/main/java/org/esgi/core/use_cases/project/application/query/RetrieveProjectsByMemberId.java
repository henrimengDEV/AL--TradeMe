package org.esgi.core.use_cases.project.application.query;

import org.esgi.kernel.cqs.Query;

public class RetrieveProjectsByMemberId implements Query {

  public final int id;

  public RetrieveProjectsByMemberId(int id) {
    this.id = id;
  }
}
