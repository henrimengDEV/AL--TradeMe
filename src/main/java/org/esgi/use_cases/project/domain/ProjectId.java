package org.esgi.use_cases.project.domain;

import org.esgi.kernel.ValueObjectId;


import java.util.Objects;

@ValueObjectId
public final class ProjectId {
    private final int value;

    private ProjectId(int id) {
        this.value = id;
    }

    public static ProjectId of(int id) {
        return new ProjectId(id);
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectId memberId= (ProjectId) o;
        return value == memberId.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "ProjectId{" +
                "value=" + value +
                '}';
    }

}
