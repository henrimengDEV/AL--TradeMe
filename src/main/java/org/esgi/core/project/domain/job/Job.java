package org.esgi.core.project.domain.job;

import org.esgi.core.education_certificate.domain.EducationCertificateId;

public interface Job {

  JobType getJobType();

  EducationCertificateId getEducationCertificateId();

}
