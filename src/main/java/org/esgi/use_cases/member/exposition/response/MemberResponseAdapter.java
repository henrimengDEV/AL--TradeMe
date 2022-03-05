package org.esgi.use_cases.member.exposition.response;


import org.esgi.kernel.Adapter;
import org.esgi.use_cases.member.domain.model.Member;
import org.esgi.use_cases.member.domain.model.NoAddress;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class MemberResponseAdapter implements Adapter<Member, MemberResponse> {

    @Override
    public MemberResponse adapt(Member source) {
        return new MemberResponse(
                String.valueOf(source.getMemberId().getValue()),
                source.getLastname(),
                source.getFirstname(),
                source.getLogin(),
                source.getMemberRole().toString(),
                source.getMail(),
                source.getAddress() instanceof NoAddress
                        ? new AddressResponse()
                        : new AddressResponse(
                                source.getAddress().city(),
                                source.getAddress().country(),
                                source.getAddress().street(),
                                source.getAddress().zipCode()
                ));
    }
}
