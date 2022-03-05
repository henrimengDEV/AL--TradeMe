package org.esgi.use_cases.project.domain;

import org.esgi.kernel.ValueObjectId;

import java.util.Objects;

@ValueObjectId
public class ProjectContractorId {

    private final int id;

    private ProjectContractorId(int id) {
        this.id = id;
    }

    public static ProjectContractorId of(int id) {
        return new ProjectContractorId(id);
    }

    public int getValue() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectContractorId projectContractorId = (ProjectContractorId) o;
        return id == projectContractorId.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ProjectContractorId{" +
                "id=" + id +
                '}';
    }
}
