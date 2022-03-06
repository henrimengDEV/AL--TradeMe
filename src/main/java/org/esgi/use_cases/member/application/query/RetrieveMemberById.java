package org.esgi.use_cases.member.application.query;


import org.esgi.shared_kernel.cqs.Query;

public class RetrieveMemberById implements Query {
    public final int id;

    public RetrieveMemberById(int id) {
        this.id = id;
    }
}
