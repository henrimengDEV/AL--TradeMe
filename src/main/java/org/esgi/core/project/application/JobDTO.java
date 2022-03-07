package org.esgi.core.project.application;

public class JobDTO {

  public String  jobName;
  public boolean isEducationCertificateRequired;

  public JobDTO(String jobName, boolean isEducationCertificateRequired) {
    this.jobName = jobName;
    this.isEducationCertificateRequired = isEducationCertificateRequired;
  }
}
