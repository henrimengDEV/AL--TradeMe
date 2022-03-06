package org.esgi.use_cases.projects.application.query;

import org.esgi.shared_kernel.cqs.Query;

public class RetrieveProjectsByMemberId implements Query {

    public final int id;

    public RetrieveProjectsByMemberId(int id) {this.id = id;}
}
