package org.esgi.use_cases.member.domain;


import org.esgi.use_cases.member.domain.model.*;

public class MemberBuilder {
    private String     lastname;
    private String     firstname;
    private MemberId   userId;
    private String     login;
    private String     password;
    private Address    address;
    private MemberRole userType;
    private String     mail;

    public static MemberBuilder builder() {
        return new MemberBuilder();
    }

    public MemberBuilder withFirstName(String firstnameValue) {
        this.firstname = firstnameValue;
        return this;
    }

    public MemberBuilder withLastName(String lastnameValue) {
        this.lastname = lastnameValue;
        return this;
    }

    public MemberBuilder withAddress(Address address) {
        if (address instanceof NoAddress)
            throw new IllegalArgumentException("Address is required.");
        this.address = address;
        return this;
    }

    public MemberBuilder withMemberId(MemberId userId) {
        this.userId = userId;
        return this;
    }

    public MemberBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public MemberBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public MemberBuilder withMemberType(String usertype) {
        this.userType = MemberRole.fromString(usertype);
        return this;
    }

    public MemberBuilder withMail(String mail) {
        this.mail = mail;
        return this;
    }

    public DefaultMember build() {
        return DefaultMember.of(
                lastname,
                firstname,
                login,
                userId,
                password,
                address,
                userType,
                mail);
    }


}

