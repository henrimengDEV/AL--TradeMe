package org.esgi.core.use_cases.education_certificate.domain;

import java.util.List;
import org.esgi.kernel.annotations.Repository;

@Repository
public interface EducationCertificates {

  int nextIdentity();

  EducationCertificate findById(EducationCertificateId id);

  List<EducationCertificate> findAll();

  Void add(EducationCertificate educationCertificate);

  Void removeById(EducationCertificateId id);
}
