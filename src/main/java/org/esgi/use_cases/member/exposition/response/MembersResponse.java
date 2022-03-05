package org.esgi.use_cases.member.exposition.response;

import java.util.List;

public class MembersResponse {

    public final List<MemberResponse> users;

    public MembersResponse(List<MemberResponse> users) {
        this.users = users;
    }
}
