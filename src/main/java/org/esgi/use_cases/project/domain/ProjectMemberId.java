package org.esgi.use_cases.project.domain;
import org.esgi.kernel.ValueObjectId;

import java.util.Objects;

@ValueObjectId
public class ProjectMemberId {
    private final int id;

    private ProjectMemberId(int id) {
        this.id = id;
    }

    public static ProjectMemberId of(int id) {
        return new ProjectMemberId(id);
    }

    public int getValue() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectMemberId projectMemberId = (ProjectMemberId) o;
        return id == projectMemberId.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ProjectMemberId{" +
                "id=" + id +
                '}';
    }
}
