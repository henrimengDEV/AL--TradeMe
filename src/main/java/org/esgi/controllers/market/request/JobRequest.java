package org.esgi.controllers.market.request;

import javax.validation.constraints.NotBlank;

public class JobRequest {

  @NotBlank
  public String jobName;

  public boolean isEducationCertificateRequired;
}
