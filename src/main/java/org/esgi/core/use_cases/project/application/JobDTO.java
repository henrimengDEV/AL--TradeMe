package org.esgi.core.use_cases.project.application;

public class JobDTO {

  public String  jobName;
  public boolean isEducationCertificateRequired;

  public JobDTO(String jobName, boolean isEducationCertificateRequired) {
    this.jobName = jobName;
    this.isEducationCertificateRequired = isEducationCertificateRequired;
  }
}
