package org.esgi.core.use_cases.member.domain.model;

import java.util.List;
import org.esgi.core.use_cases.member.domain.GeographicZone;
import org.esgi.core.use_cases.project.domain.job.JobType;
import org.esgi.core.use_cases.project.domain.project.ProjectId;

public class NoMember implements Member {

  @Override
  public String getLastname() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getFirstname() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getLogin() {
    throw new UnsupportedOperationException();
  }

  @Override
  public MemberId getMemberId() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Address getAddress() {
    throw new UnsupportedOperationException();
  }

  @Override
  public MemberRole getMemberRole() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getMail() {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<JobType> getCompetences() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Boolean isSubscribed() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void addMemberId(int id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void changeAddress(Address address) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void changeIsSubscribed(boolean isSubscribed) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<ProjectId> getProjects() {
    throw new UnsupportedOperationException();
  }

  @Override
  public GeographicZone getGeographicZoneOfAvailability() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void addProject(ProjectId projectId) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void addCompetence(JobType job) {
    throw new UnsupportedOperationException();
  }
}
