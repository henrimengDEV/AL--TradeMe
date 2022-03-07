package org.esgi.core.project.application.command;

import java.time.LocalDate;
import java.util.List;
import org.esgi.core.member.application.AddressDTO;
import org.esgi.core.project.application.DailyRemunerationDTO;
import org.esgi.core.project.application.JobDTO;
import org.esgi.kernel.cqs.Command;

/**
 * Command object
 */
@SuppressWarnings("all")
public final class CreateProject implements Command {

  public final Integer              contractorId;
  public final String               name;
  public final LocalDate            startDate;
  public final LocalDate            endDate;
  public final List<JobDTO>         requiredJobs;
  public final DailyRemunerationDTO dailyRemunerationDTO;
  public final AddressDTO           address;


  public CreateProject(Integer contractorId, String name, LocalDate startDate, LocalDate endDate,
                       List<JobDTO> requiredJobs, DailyRemunerationDTO dailyRemunerationDTO, AddressDTO address) {
    this.contractorId = contractorId;
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
    this.requiredJobs = requiredJobs;
    this.dailyRemunerationDTO = dailyRemunerationDTO;
    this.address = address;
  }
}
