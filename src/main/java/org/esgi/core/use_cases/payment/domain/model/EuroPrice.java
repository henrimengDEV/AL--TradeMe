package org.esgi.core.use_cases.payment.domain.model;

import static org.esgi.core.use_cases.payment.domain.model.DeviseType.EURO;

public class EuroPrice implements Price {

    DeviseType deviseType;
    int                                                   value;

    public EuroPrice(int value) {
        this.deviseType = EURO;
        this.value = value;
    }

    public static EuroPrice of(int value){
        return new EuroPrice(value);
    }

    @Override public DeviseType getDevise() {
        return this.deviseType;
    }

    @Override public Integer getValue() {
        return this.value;
    }
}
