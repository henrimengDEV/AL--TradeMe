package org.esgi.core.use_cases.payment.domain.model.price;

public interface Price {

  DeviseType getDevise();

  Integer getValue();

}
