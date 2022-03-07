package org.esgi.core.use_cases.project.domain.job;

import org.esgi.core.use_cases.education_certificate.domain.EducationCertificateId;

public interface Job {

  JobType getJobType();

  EducationCertificateId getEducationCertificateId();

}
