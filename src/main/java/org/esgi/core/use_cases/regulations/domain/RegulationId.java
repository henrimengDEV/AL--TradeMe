package org.esgi.core.use_cases.regulations.domain;

import org.esgi.kernel.ValueObjectId;

public final class RegulationId implements ValueObjectId {
    private final int value;

    private RegulationId(int value) {this.value = value;}

    public static RegulationId of (int value) {
        return new RegulationId(value);
    }


    @Override
    public int getValue() {
        return value;
    }
}
