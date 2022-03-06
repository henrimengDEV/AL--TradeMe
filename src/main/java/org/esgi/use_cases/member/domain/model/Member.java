package org.esgi.use_cases.member.domain.model;

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

}
