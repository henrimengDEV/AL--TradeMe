package org.esgi.core.use_cases.payment.domain.model;

public interface Price {

    DeviseType getDevise();

    Integer getValue();

}
