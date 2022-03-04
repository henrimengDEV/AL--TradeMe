package org.esgi.use_cases.education_certificate.domain;

import org.esgi.kernel.Repository;
import org.esgi.use_cases.contractor.domain.Contractor;

import java.util.List;

@Repository
public interface EducationCertificates {
    int nextIdentity();
    EducationCertificate findById(EducationCertificateId id);
    List<EducationCertificate> findAll();
    Void add(EducationCertificate educationCertificate);
    Void removeById(EducationCertificateId id);
}
