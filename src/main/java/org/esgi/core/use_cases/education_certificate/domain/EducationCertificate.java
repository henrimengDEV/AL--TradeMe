package org.esgi.core.use_cases.education_certificate.domain;

import java.util.Objects;
import org.esgi.core.use_cases.project.domain.job.JobType;

public final class EducationCertificate {

  private final EducationCertificateId educationCertificateId;
  private final JobType                jobType;
  private final String                 name;

  private EducationCertificate(EducationCertificateId educationCertificateId,
                               JobType jobType, String name) {
    this.educationCertificateId = educationCertificateId;
    this.jobType = jobType;
    this.name = name;
  }

  public static EducationCertificate of(EducationCertificateId educationCertificateId,
                                        JobType jobType, String name) {
    return new EducationCertificate(educationCertificateId, jobType, name);
  }

  public EducationCertificateId getEducationCertificateId() {
    return educationCertificateId;
  }

  public JobType getJobType() {
    return jobType;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }
      if (o == null || getClass() != o.getClass()) {
          return false;
      }
    EducationCertificate that = (EducationCertificate) o;
    return Objects.equals(educationCertificateId, that.educationCertificateId)
        && jobType == that.jobType && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(educationCertificateId, jobType, name);
  }

  @Override
  public String toString() {
    return "EducationCertificate{" +
        "educationCertificateId=" + educationCertificateId +
        ", jobType=" + jobType +
        ", name='" + name + '\'' +
        '}';
  }
}

