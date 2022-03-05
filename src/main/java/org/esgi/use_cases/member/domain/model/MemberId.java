package org.esgi.use_cases.member.domain.model;

import org.esgi.kernel.ValueObjectId;

import java.util.Objects;

public final class MemberId implements ValueObjectId {
    private final int value;

    public MemberId(int value) {
        this.value = value;
    }

    public static MemberId of(int value) {
        return new MemberId(value);
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberId userId = (MemberId) o;
        return value == userId.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "UserId{" +
                "value=" + value +
                '}';
    }
}