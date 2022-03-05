package org.esgi.use_cases.member.domain.model;

public enum MemberRole {
    TRADESMAN("tradesman"),
    CONTRACTOR("contractor");

    private final String role;

    MemberRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    public static MemberRole fromString(String text) {
        for (MemberRole val : MemberRole.values()) {
            if (val.role.equalsIgnoreCase(text)) {
                return val;
            }
        }
        return null;
    }


}
