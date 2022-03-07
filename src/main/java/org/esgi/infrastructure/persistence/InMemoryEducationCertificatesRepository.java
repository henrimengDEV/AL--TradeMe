package org.esgi.infrastructure.persistence;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.esgi.core.education_certificate.domain.EducationCertificate;
import org.esgi.core.education_certificate.domain.EducationCertificateId;
import org.esgi.core.education_certificate.domain.EducationCertificates;
import org.esgi.kernel.annotations.RepositoryImpl;

@RepositoryImpl
public final class InMemoryEducationCertificatesRepository implements EducationCertificates {

  private final AtomicInteger                                     count = new AtomicInteger();
  private final Map<EducationCertificateId, EducationCertificate> data  = new ConcurrentHashMap<>();

  @Override
  public int nextIdentity() {
    return 0;
  }

  @Override
  public EducationCertificate findById(EducationCertificateId id) {
    return null;
  }

  @Override
  public List<EducationCertificate> findAll() {
    return null;
  }

  @Override
  public Void add(EducationCertificate educationCertificate) {
    return null;
  }

  @Override
  public Void removeById(EducationCertificateId id) {
    return null;
  }
}
