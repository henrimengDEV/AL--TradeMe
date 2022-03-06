package org.esgi.controllers.membership.response;


import org.esgi.kernel.Adapter;
import org.esgi.core.use_cases.member.domain.model.Member;
import org.esgi.core.use_cases.member.domain.model.NoAddress;
import org.esgi.core.use_cases.projects.domain.ProjectId;

import java.util.Objects;
import java.util.stream.Collectors;

public class MemberResponseAdapter implements Adapter<Member, MemberResponse> {

    @Override
    public MemberResponse adapt(Member source) {
        return new MemberResponse(
                source.getMemberId().getValue(),
                source.getLastname(),
                source.getFirstname(),
                source.getLogin(),
                source.getMemberRole().toString(),
                source.getMail(),
                source.isSubscribed());
    }

    public MemberWithAddressResponse adaptWithAddress(Member source) {
        return new MemberWithAddressResponse(
                source.getMemberId().getValue(),
                source.getLastname(),
                source.getFirstname(),
                source.getLogin(),
                source.getMemberRole().toString(),
                source.getMail(),
                source.isSubscribed(),
                source.getAddress() instanceof NoAddress
                        ? new AddressResponse()
                        : new AddressResponse(
                                source.getAddress().city(),
                                source.getAddress().country(),
                                source.getAddress().street(),
                                source.getAddress().zipCode()
                        ));
    }

    public MemberResponse adaptWithProjects(Member source) {
        return new MemberWithProjectsResponse(
                source.getMemberId().getValue(),
                source.getLastname(),
                source.getFirstname(),
                source.getLogin(),
                source.getMemberRole().toString(),
                source.getMail(),
                source.isSubscribed(),
                source.getProjects().stream()
                      .map(ProjectId::getValue)
                      .collect(Collectors.toList())
        );
    }
    public MemberResponse adaptWithAll(Member source) {
        return new MemberWithAllResponse(
                source.getMemberId().getValue(),
                source.getLastname(),
                source.getFirstname(),
                source.getLogin(),
                source.getMemberRole().toString(),
                source.getMail(),
                source.isSubscribed(),
                source.getAddress() instanceof NoAddress
                ? new AddressResponse()
                : new AddressResponse(
                        source.getAddress().city(),
                        source.getAddress().country(),
                        source.getAddress().street(),
                        source.getAddress().zipCode()
                ),
                Objects.isNull(source.getProjects())
                        ? null
                        : source.getProjects().stream()
                              .map(ProjectId::getValue)
                              .collect(Collectors.toList())
        );
    }

}
