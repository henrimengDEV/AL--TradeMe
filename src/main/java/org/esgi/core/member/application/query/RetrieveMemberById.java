package org.esgi.core.member.application.query;


import org.esgi.kernel.cqs.Query;

public class RetrieveMemberById implements Query {

  public final int id;

  public RetrieveMemberById(int id) {
    this.id = id;
  }
}
