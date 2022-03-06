package org.esgi.use_cases.projects.application.query;

import org.esgi.shared_kernel.cqs.Query;

public class RetrieveProjectById implements Query {

    public final int id;

    public RetrieveProjectById(int id) {this.id = id;}
}
