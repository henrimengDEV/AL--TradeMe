package org.esgi.use_cases.project.domain;

import org.esgi.kernel.AggregateRoot;

import java.util.Objects;

@AggregateRoot
public final class Project {

    private final ProjectId id;
    private final String name;
    private final ProjectAddress address;

    private final ProjectContractorId contractorId;
    private final ProjectMemberId memberId;

    // TODO
    // Metier
    // Competence
    // Taux journalier
    // Durée

    private Project(ProjectId id, String name, ProjectAddress address, ProjectContractorId contractorId, ProjectMemberId memberId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contractorId = contractorId;
        this.memberId = memberId;
    }

    public static Project of(ProjectId id, String name, ProjectAddress address, ProjectContractorId contractorId, ProjectMemberId memberId) {
        return new Project(id, name, address, contractorId, memberId);
    }

    public ProjectId getProjetId() {
        return id;
    }

    public ProjectContractorId getContractorId() {
        return contractorId;
    }

    public ProjectMemberId getMemberId() {
        return memberId;
    }

    public String getProjectName() {
        return name;
    }

    public ProjectAddress getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id )
                && Objects.equals(name, project.name)
                && Objects.equals(address, project.address)
                && Objects.equals(contractorId, project.contractorId)
                && Objects.equals(memberId, project.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, contractorId, memberId);
    }

    @Override
    public String toString() {

        return "Member{" +
                "id = " + id + '\'' +
                ", name = " + name + '\'' +
                ", address = " + address + '\'' +
                ", contractorId = " + contractorId + '\'' +
                ", memberId = " + memberId + '\'' +
                '}';
    }

}
