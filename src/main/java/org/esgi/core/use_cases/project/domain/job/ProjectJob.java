package org.esgi.core.use_cases.project.domain.job;


import org.esgi.core.use_cases.education_certificate.domain.EducationCertificateId;


public final class ProjectJob implements Job {

  private final JobType                jobType;
  private final EducationCertificateId educationCertificateId;
  private final boolean                isCertificateRequired;

  private ProjectJob(JobType jobType,
                     boolean isCertificateRequired) {
    this.jobType = jobType;
    this.educationCertificateId = jobType.getEducationCertificateId();
    this.isCertificateRequired = isCertificateRequired;
  }

  public static ProjectJob of(JobType jobType, boolean isCertificateRequired) {
    return new ProjectJob(jobType, isCertificateRequired);
  }


  @Override
  public JobType getJobType() {
    return jobType;
  }

  public EducationCertificateId getEducationCertificateId() {
    return educationCertificateId;
  }

  public boolean isCertificateRequired() {
    return isCertificateRequired;
  }

}
