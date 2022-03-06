package org.esgi.core.use_cases.member.domain;


import org.esgi.core.use_cases.member.domain.model.Address;
import org.esgi.core.use_cases.member.domain.model.DefaultMember;
import org.esgi.core.use_cases.member.domain.model.MemberId;
import org.esgi.core.use_cases.member.domain.model.MemberRole;
import org.esgi.core.use_cases.projects.domain.ProjectId;

import java.util.List;

public class MemberBuilder {
    private String     lastname;
    private String     firstname;
    private MemberId   memberId;
    private String     login;
    private String     password;
    private Address    address;
    private MemberRole memberRole;
    private String     mail;
    private boolean         isSubscribed;
    private List<ProjectId> projects;

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
        this.address = address;
        return this;
    }

    public MemberBuilder withMemberId(MemberId memberId) {
        this.memberId = memberId;
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

    public MemberBuilder withMemberRole(String memberRole) {
        this.memberRole = MemberRole.fromString(memberRole);
        return this;
    }

    public MemberBuilder withMail(String mail) {
        this.mail = mail;
        return this;
    }

    public MemberBuilder withIsSubscribed(boolean isSubscribed) {
        this.isSubscribed = isSubscribed;
        return this;
    }
    public MemberBuilder withProjects(List<ProjectId> projects) {
        this.projects = projects;
        return this;
    }

    public DefaultMember build() {
        return DefaultMember.of(
                lastname,
                firstname,
                login,
                memberId,
                password,
                address,
                memberRole,
                mail,
                projects,
                isSubscribed);
    }


}

