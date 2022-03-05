package org.esgi.use_cases.regulation.domain;

import org.esgi.kernel.ValueObjectId;

import java.util.Objects;

@ValueObjectId
public final class RegulationId {

    private final int value;

    public RegulationId(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "RegulationId{" +
                "value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegulationId that = (RegulationId) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
