package org.esgi.core.use_cases.workflows.domain;


import org.esgi.kernel.ValueObjectId;

import java.util.Objects;

public final class WorkflowsId implements ValueObjectId {
    private final int value;

    public WorkflowsId(int value) {
        this.value = value;
    }

    public static WorkflowsId of(int value) {
        return new WorkflowsId(value);
    }

    public int getValue() {
        return value;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkflowsId that = (WorkflowsId) o;
        return value == that.value;
    }

    @Override public int hashCode() {
        return Objects.hash(value);
    }

    @Override public String toString() {
        return "WorkflowsId{" +
                "value=" + value +
                '}';
    }
}
