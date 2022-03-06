package org.esgi.core.use_cases.member.domain.model;

import org.esgi.core.use_cases.projects.domain.ProjectId;

import java.util.List;

public interface Member {

    String getLastname();

    String getFirstname();

    String getLogin();

    MemberId getMemberId();

    Address getAddress();

    MemberRole getMemberRole();

    String getMail();

    Boolean isSubscribed();

    void addMemberId(int id);

    void changeAddress(Address address);

    void changeIsSubscribed(boolean isSubscribed);

    List<ProjectId> getProjects();
}
