package org.esgi.use_cases.member.domain.model;

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

    @Override public String toStringWithAddress() {
        throw new UnsupportedOperationException();
    }

    @Override public void addMemberId(int id) {
        throw new UnsupportedOperationException();
    }

    @Override public void changeAddress(Address address) {
        throw new UnsupportedOperationException();
    }
}
