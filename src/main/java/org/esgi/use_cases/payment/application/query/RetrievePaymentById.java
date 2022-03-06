package org.esgi.use_cases.payment.application.query;


import org.esgi.shared_kernel.cqs.Query;

public class RetrievePaymentById implements Query {
    public final int id;

    public RetrievePaymentById(int id) {
        this.id = id;
    }
}
