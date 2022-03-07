package org.esgi.core.use_cases.project.domain.job;


import org.esgi.core.use_cases.education_certificate.domain.EducationCertificateId;

public enum JobType {
  ARCHITECT("architect", EducationCertificateId.of(1)),
  ELECTRICIAN("electrician", EducationCertificateId.of(2)),
  MASON("mason", EducationCertificateId.of(3)),
  PLUMBER("plumber", EducationCertificateId.of(4)),
  CARPENTER("carpenter", EducationCertificateId.of(5));

  private final String                 role;
  private final EducationCertificateId educationCertificateId;

  JobType(String role,
          EducationCertificateId educationCertificateId) {
    this.role = role;
    this.educationCertificateId = educationCertificateId;
  }

  public String getValue() {
    return this.role;
  }

  public EducationCertificateId getEducationCertificateId() {
    return this.educationCertificateId;
  }

  public static JobType fromString(String text) {
    for (JobType val : JobType.values()) {
      if (val.role.equalsIgnoreCase(text)) {
        return val;
      }
    }
    return null;
  }

  public static JobType fromEducationCertificateId(EducationCertificateId id) {
    for (JobType val : JobType.values()) {
      if (val.educationCertificateId.equals(id)) {
        return val;
      }
    }
    return null;
  }


}
