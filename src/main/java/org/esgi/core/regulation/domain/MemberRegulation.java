package org.esgi.core.regulation.domain;

import java.util.Objects;
import org.esgi.core.member.domain.model.MemberId;

public class MemberRegulation implements Regulation {

  private final RegulationId regulationId;
  private final String   description;
  private final MemberId memberId;

  private MemberRegulation(RegulationId regulationId, String description, MemberId memberId) {
    this.regulationId = regulationId;
    this.description = description;
    this.memberId = memberId;
  }

  public static MemberRegulation of(RegulationId regulationId, String description, MemberId memberId) {
    return new MemberRegulation(regulationId, description, memberId);
  }

  @Override
  public RegulationId getRegulationId() {
    return regulationId;
  }

  public String getDescription() {
    return description;
  }

  public MemberId getMemberId() {
    return memberId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MemberRegulation that = (MemberRegulation) o;
    return Objects.equals(regulationId, that.regulationId) && Objects.equals(description, that.description)
        && Objects.equals(memberId, that.memberId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(regulationId, description, memberId);
  }

  @Override
  public String toString() {
    return "MemberRegulation{" + "regulationId=" + regulationId + ", description='" + description + '\'' + ", memberId="
        + memberId + '}';
  }
}
