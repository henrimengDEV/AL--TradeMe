package org.esgi.core.use_cases.member.domain.model;

import java.util.List;
import org.esgi.core.use_cases.member.domain.GeographicZone;
import org.esgi.core.use_cases.project.domain.job.JobType;
import org.esgi.core.use_cases.project.domain.project.ProjectId;

public interface Member {

  String getLastname();

  String getFirstname();

  String getLogin();

  MemberId getMemberId();

  Address getAddress();

  MemberRole getMemberRole();

  String getMail();

  List<JobType> getCompetences();

  Boolean isSubscribed();

  void addMemberId(int id);

  void changeAddress(Address address);

  void changeIsSubscribed(boolean isSubscribed);

  List<ProjectId> getProjects();

  GeographicZone getGeographicZoneOfAvailability();

  void addProject(ProjectId projectId);

  void addCompetence(JobType job);
}
