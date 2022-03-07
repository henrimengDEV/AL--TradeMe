package org.esgi.core.member.domain.model;

public enum MemberRole {
  TRADESMAN("tradesman"), CONTRACTOR("contractor");

  private final String role;

  MemberRole(String role) {
    this.role = role;
  }

  public static MemberRole fromString(String text) {
    for (MemberRole val : MemberRole.values()) {
      if (val.role.equalsIgnoreCase(text)) {
        return val;
      }
    }
    return null;
  }

  public String getValue() {
    return this.role;
  }


}
