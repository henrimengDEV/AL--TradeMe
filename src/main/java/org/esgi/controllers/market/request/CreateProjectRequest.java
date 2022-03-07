package org.esgi.controllers.market.request;

import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.esgi.controllers.membership.request.AddressRequest;

public class CreateProjectRequest {

  @NotNull
  public Integer contractorId;

  @NotBlank
  public String name;

  @NotNull
  public LocalDate startDate;

  @NotNull
  public LocalDate endDate;

  @NotNull
  public List<JobRequest> requiredJobs;

  @NotNull
  public Integer dailyRemuneration;

  @NotBlank
  public String devise;

  @NotNull
  public AddressRequest address;
}
