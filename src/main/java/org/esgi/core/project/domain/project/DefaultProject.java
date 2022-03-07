package org.esgi.core.project.domain.project;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import org.esgi.core.contractor.domain.ContractorId;
import org.esgi.core.member.domain.model.Address;
import org.esgi.core.member.domain.model.MemberId;
import org.esgi.core.project.domain.DailyRemuneration;
import org.esgi.core.project.domain.job.ProjectJob;

public class DefaultProject implements Project {

  private final ProjectId         projectId;
  private final ContractorId      contractorId;
  private final String            name;
  private final Period            period;
  private final List<ProjectJob>  requiredJobs;
  private final DailyRemuneration dailyRemuneration;
  private final Address           address;
  private       List<MemberId>    particpants;
  private       boolean           isActive;

  public DefaultProject(ProjectId projectId, ContractorId contractorId, String name, Period period,
                        List<ProjectJob> requiredJobs, DailyRemuneration dailyRemuneration, Address address) {
    this.projectId = projectId;
    this.contractorId = contractorId;
    this.particpants = new ArrayList<>();
    this.name = name;
    this.period = period;
    this.requiredJobs = requiredJobs;
    this.dailyRemuneration = dailyRemuneration;
    this.address = address;
    this.isActive = false;
  }

  public static DefaultProject of(ProjectId projectId, ContractorId contractorId, String name, Period period,
                                  List<ProjectJob> requiredJobs, DailyRemuneration dailyRemuneration, Address address) {
    return new DefaultProject(projectId, contractorId, name, period, requiredJobs, dailyRemuneration, address);
  }

  @Override
  public ProjectId getProjectId() {
    return projectId;
  }

  @Override
  public List<MemberId> getParticipants() {
    return particpants;
  }

  @Override
  public ContractorId getContractorId() {
    return contractorId;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Period getPeriod() {
    return period;
  }

  @Override
  public List<ProjectJob> getRequiredJobs() {
    return requiredJobs;
  }

  @Override
  public DailyRemuneration getDailyRemuneration() {
    return dailyRemuneration;
  }

  @Override
  public Address getAddress() {
    return address;
  }

  @Override
  public void addParticipant(MemberId memberId) {
    particpants.add(memberId);
  }

  @Override
  public void activateProject() {
    isActive = true;
  }

  @Override
  public void desactivateProject() {
    isActive = false;
  }

  @Override
  public String toString() {
    return "DefaultProject{" + "projectId=" + projectId + ", contractorId=" + contractorId + ", particpants="
        + particpants + ", name='" + name + '\'' + ", period=" + period + ", requiredJobs=" + requiredJobs
        + ", dailyRemuneration=" + dailyRemuneration + ", address=" + address + ", isActive=" + isActive + '}';
  }
}
