package org.esgi.core.use_cases.member.domain.model;

import org.esgi.core.use_cases.projects.domain.ProjectId;

import java.util.List;

public class NoMember implements Member {
    @Override public String getLastname() {
        throw new UnsupportedOperationException();
    }

    @Override public String getFirstname() {
        throw new UnsupportedOperationException();
    }

    @Override public String getLogin() {
        throw new UnsupportedOperationException();
    }

    @Override public MemberId getMemberId() {
        throw new UnsupportedOperationException();
    }

    @Override public Address getAddress() {
        throw new UnsupportedOperationException();
    }

    @Override public MemberRole getMemberRole() {
        throw new UnsupportedOperationException();
    }

    @Override public String getMail() {
        throw new UnsupportedOperationException();
    }

    @Override public Boolean isSubscribed() {
        throw new UnsupportedOperationException();
    }

    @Override public void addMemberId(int id) {
        throw new UnsupportedOperationException();
    }

    @Override public void changeAddress(Address address) {
        throw new UnsupportedOperationException();
    }

    @Override public void changeIsSubscribed(boolean isSubscribed) {
        throw new UnsupportedOperationException();
    }

    @Override public List<ProjectId> getProjects() {
        throw new UnsupportedOperationException();
    }
}
