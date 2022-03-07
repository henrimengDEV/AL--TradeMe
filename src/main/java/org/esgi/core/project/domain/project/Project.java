package org.esgi.core.project.domain.project;

import java.time.Period;
import java.util.List;
import org.esgi.core.contractor.domain.ContractorId;
import org.esgi.core.member.domain.model.Address;
import org.esgi.core.member.domain.model.MemberId;
import org.esgi.core.project.domain.DailyRemuneration;
import org.esgi.core.project.domain.job.ProjectJob;

public interface Project {

  ProjectId getProjectId();

  List<MemberId> getParticipants();

  ContractorId getContractorId();

  String getName();

  Period getPeriod();

  List<ProjectJob> getRequiredJobs();

  DailyRemuneration getDailyRemuneration();

  Address getAddress();

  void addParticipant(MemberId memberId);

  void activateProject();

  void desactivateProject();

}
