package org.esgi.use_cases.member.port.response;


import org.esgi.shared_kernel.Adapter;
import org.esgi.use_cases.member.domain.model.Member;
import org.esgi.use_cases.member.domain.model.NoAddress;

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
}
