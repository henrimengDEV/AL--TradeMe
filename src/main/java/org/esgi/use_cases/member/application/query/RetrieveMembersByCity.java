package org.esgi.use_cases.member.application.query;

import org.esgi.shared_kernel.cqs.Query;

public class RetrieveMembersByCity implements Query {
    public final String city;

    public RetrieveMembersByCity(String city) {
        this.city = city;
    }
}
