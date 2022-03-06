package org.esgi.use_cases.member.application.query;


import org.esgi.shared_kernel.cqs.Query;

public class RetrieveMembersByRole implements Query {
    public final String role;

    public RetrieveMembersByRole(String role) {
        this.role = role;
    }
}
