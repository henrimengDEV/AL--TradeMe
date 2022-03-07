package org.esgi.core.project.domain.job;

import org.esgi.core.education_certificate.domain.EducationCertificateId;

public class DefaultJob implements Job {

  private final JobType                jobType;
  private final EducationCertificateId educationCertificateId;

  private DefaultJob(JobType jobType, EducationCertificateId educationCertificateId) {
    this.jobType = jobType;

    this.educationCertificateId = educationCertificateId;
  }

  public static DefaultJob of(JobType jobType, EducationCertificateId educationCertificateId) {
    return new DefaultJob(jobType, educationCertificateId);
  }


  @Override
  public JobType getJobType() {
    return jobType;
  }

  @Override
  public EducationCertificateId getEducationCertificateId() {
    return educationCertificateId;
  }

  @Override
  public String toString() {
    return "DefaultJob{" + "jobType=" + jobType + ", educationCertificateId=" + educationCertificateId + '}';
  }
}
