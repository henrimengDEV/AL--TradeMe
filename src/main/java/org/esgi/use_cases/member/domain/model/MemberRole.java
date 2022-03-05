package org.esgi.use_cases.member.domain.model;

public enum MemberRole {
    TRADESMAN("tradesman"),
    CONTRACTOR("contractor");

    private final String memberType;

    MemberRole(String memberType) {
        this.memberType = memberType;
    }

    public String getMemberType() {
        return this.memberType;
    }

    public static MemberRole fromString(String text) {
        for (MemberRole val : MemberRole.values()) {
            if (val.memberType.equalsIgnoreCase(text)) {
                return val;
            }
        }
        return null;
    }


}
