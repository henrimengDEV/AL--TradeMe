package org.esgi.use_cases.payment.application.query;


import org.esgi.shared_kernel.cqs.Query;

public class RetrievePaymentByMemberId implements Query {
    public final int id;

    public RetrievePaymentByMemberId(int id) {
        this.id = id;
    }
}
