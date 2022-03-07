package org.esgi.core.education_certificate.domain;

import java.util.Objects;
import org.esgi.kernel.ValueObjectId;

public final class EducationCertificateId implements ValueObjectId {

  private final int value;

  public EducationCertificateId(int value) {
    this.value = value;
  }

  public static EducationCertificateId of(int value) {
    return new EducationCertificateId(value);
  }

  public int getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EducationCertificateId that = (EducationCertificateId) o;
    return value == that.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return "EducationCertificateId{" + "value=" + value + '}';
  }
}
