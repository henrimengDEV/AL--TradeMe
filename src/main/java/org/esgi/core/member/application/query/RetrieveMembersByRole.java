package org.esgi.core.member.application.query;


import org.esgi.kernel.cqs.Query;

public class RetrieveMembersByRole implements Query {

  public final String role;

  public RetrieveMembersByRole(String role) {
    this.role = role;
  }
}
