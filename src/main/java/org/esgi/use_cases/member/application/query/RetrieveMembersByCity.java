package org.esgi.use_cases.member.application.query;

import org.esgi.kernel.cqs.Query;

public class RetrieveMembersByCity implements Query {
    public final String city;

    public RetrieveMembersByCity(String city) {
        this.city = city;
    }
}
