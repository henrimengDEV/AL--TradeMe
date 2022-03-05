package org.esgi.use_cases.member.application.query;


import org.esgi.kernel.cqs.Query;

public class RetrieveUserById implements Query {
    public final int id;

    public RetrieveUserById(int id) {
        this.id = id;
    }
}
