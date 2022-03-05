package org.esgi.use_cases.member.application.query;

import org.esgi.kernel.cqs.Query;

public class RetrieveUsersByCity implements Query {
    public final String city;

    public RetrieveUsersByCity(String city) {
        this.city = city;
    }
}
