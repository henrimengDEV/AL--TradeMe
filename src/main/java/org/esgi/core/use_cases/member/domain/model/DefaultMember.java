package org.esgi.core.use_cases.member.domain.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.esgi.core.use_cases.member.domain.GeographicZone;
import org.esgi.core.use_cases.project.domain.job.JobType;
import org.esgi.core.use_cases.project.domain.project.ProjectId;

public class DefaultMember implements Member {

  private final String          lastname;
  private final String          firstname;
  private final String          login;
  private final GeographicZone  geographicZoneOfAvailability;
  private       MemberId        memberId;
  private final String          password;
  private       Address         address;
  private final MemberRole      memberRole;
  private final String          mail;
  private       boolean         isSubscribed;
  private       List<ProjectId> projects;
  private final List<JobType>   competences;

  private DefaultMember(String lastname,
                        String firstname,
                        String login,
                        MemberId memberId,
                        String password,
                        Address address,
                        MemberRole memberRole,
                        String mail,
                        Boolean isSubscribed,
                        GeographicZone geographicZoneOfAvailability,
                        List<JobType> competences) {
    this.lastname = lastname;
    this.firstname = firstname;
    this.login = login;
    this.memberId = memberId;
    this.password = password;
    this.address = address;
    this.memberRole = memberRole;
    this.mail = mail;
    this.isSubscribed = !Objects.isNull(isSubscribed) && isSubscribed;
    this.geographicZoneOfAvailability = Objects.isNull(geographicZoneOfAvailability)
                                        ? GeographicZone.COUNTRY
                                        : geographicZoneOfAvailability;
    this.competences = competences;
    this.projects = new ArrayList<>();
  }

  public static DefaultMember of(String lastname,
                                 String firstname,
                                 String login,
                                 MemberId memberId,
                                 String password,
                                 Address address,
                                 MemberRole memberRole,
                                 String mail,
                                 Boolean isSubscribed,
                                 GeographicZone geographicZoneOfAvailability,
                                 List<JobType> competences) {
    return new DefaultMember(lastname, firstname, login, memberId, password, address, memberRole,
                             mail, isSubscribed, geographicZoneOfAvailability, competences);
  }

  @Override
  public String getLastname() {
    return lastname;
  }

  @Override
  public String getFirstname() {
    return firstname;
  }

  @Override
  public String getLogin() {
    return login;
  }

  @Override
  public MemberId getMemberId() {
    return memberId;
  }

  @Override
  public Address getAddress() {
    return address;
  }

  @Override
  public MemberRole getMemberRole() {
    return memberRole;
  }

  @Override
  public String getMail() {
    return mail;
  }

  @Override
  public List<JobType> getCompetences() {
    return competences;
  }

  @Override
  public Boolean isSubscribed() {
    return isSubscribed;
  }

  @Override
  public void addMemberId(int id) {
    this.memberId = new MemberId(id);
  }

  @Override
  public void changeAddress(Address address) {
    this.address = address;
  }

  @Override
  public void changeIsSubscribed(boolean isSubscribed) {
    this.isSubscribed = isSubscribed;
  }

  @Override
  public List<ProjectId> getProjects() {
    return projects;
  }

  @Override
  public GeographicZone getGeographicZoneOfAvailability() {
    return geographicZoneOfAvailability;
  }

  @Override
  public void addProject(ProjectId projectId) {
    projects.add(projectId);
  }

  @Override
  public void addCompetence(JobType job) {
    competences.add(job);
  }

  @Override
  public String toString() {
    return "DefaultMember{" +
        "lastname='" + lastname + '\'' +
        ", firstname='" + firstname + '\'' +
        ", login='" + login + '\'' +
        ", geographicZoneOfAvailability=" + geographicZoneOfAvailability +
        ", memberId=" + memberId +
        ", password='" + password + '\'' +
        ", address=" + address +
        ", memberRole=" + memberRole +
        ", mail='" + mail + '\'' +
        ", isSubscribed=" + isSubscribed +
        ", projects=" + projects +
        '}';
  }
}
