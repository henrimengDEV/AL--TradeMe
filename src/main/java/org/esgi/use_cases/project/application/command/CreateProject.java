package org.esgi.use_cases.project.application.command;

import org.esgi.kernel.Command;
import org.esgi.use_cases.project.domain.ProjectAddress;
import org.esgi.use_cases.project.domain.ProjectContractorId;
import org.esgi.use_cases.project.domain.ProjectMemberId;

public class CreateProject implements Command {

    private final String name;
    private final ProjectAddress address;
    private final ProjectContractorId contractorId;
    private final ProjectMemberId memberId;

    public CreateProject(String name, ProjectAddress address, ProjectContractorId contractorId, ProjectMemberId memberId) {
        this.name = name;
        this.address = address;
        this.contractorId = contractorId;
        this.memberId = memberId;
    }

    public static CreateProject of(String name, ProjectAddress address, ProjectContractorId contractorId, ProjectMemberId memberId) {
        return new CreateProject(name, address, contractorId, memberId);
    }

    public String getName() {
        return name;
    }

    public ProjectAddress getAddress() {
        return address;
    }

    public ProjectContractorId getContractorId() {
        return contractorId;
    }

    public ProjectMemberId getMemberId() {
        return memberId;
    }
}
