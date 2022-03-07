package org.esgi.core.member.domain;


import java.util.List;
import org.esgi.core.member.domain.model.MemberId;
import org.esgi.core.project.domain.project.ProjectId;
import org.esgi.core.member.domain.model.Address;
import org.esgi.core.member.domain.model.DefaultMember;
import org.esgi.core.member.domain.model.MemberRole;
import org.esgi.core.project.domain.job.JobType;

public class MemberBuilder {

  private String          lastname;
  private String          firstname;
  private MemberId        memberId;
  private String          login;
  private String          password;
  private Address         address;
  private MemberRole      memberRole;
  private String          mail;
  private boolean         isSubscribed;
  private List<ProjectId> projects;
  private GeographicZone  geographicZoneOfAvailability;
  private List<JobType>   competences;

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

  public MemberBuilder withGeographicZoneOfAvailability(GeographicZone geographicZoneOfAvailability) {
    this.geographicZoneOfAvailability = geographicZoneOfAvailability;
    return this;
  }

  public DefaultMember build() {
    return DefaultMember.of(lastname, firstname, login, memberId, password, address, memberRole, mail, isSubscribed,
                            geographicZoneOfAvailability, competences);
  }

  public MemberBuilder withCompetences(List<JobType> competences) {
    this.competences = competences;
    return this;
  }
}

