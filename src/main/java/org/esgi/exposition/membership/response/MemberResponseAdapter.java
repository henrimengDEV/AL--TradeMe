package org.esgi.exposition.membership.response;


import java.util.Objects;
import java.util.stream.Collectors;
import org.esgi.core.member.domain.model.Member;
import org.esgi.core.member.domain.model.NoAddress;
import org.esgi.core.project.domain.project.ProjectId;
import org.esgi.kernel.Adapter;

public class MemberResponseAdapter implements Adapter<Member, MemberResponse> {

  @Override
  public MemberResponse adapt(Member source) {
    return new MemberResponse(source.getMemberId().getValue(), source.getLastname(), source.getFirstname(),
                              source.getLogin(), source.getMemberRole().toString(), source.getMail(),
                              source.isSubscribed());
  }

  public MemberWithAddressResponse adaptWithAddress(Member source) {
    return new MemberWithAddressResponse(source.getMemberId().getValue(), source.getLastname(), source.getFirstname(),
                                         source.getLogin(), source.getMemberRole().toString(), source.getMail(),
                                         source.isSubscribed(),
                                         source.getAddress() instanceof NoAddress ? new AddressResponse()
                                                                                  : new AddressResponse(
                                                                                      source.getAddress().city(),
                                                                                      source.getAddress().country(),
                                                                                      source.getAddress().street(),
                                                                                      source.getAddress().zipCode()));
  }

  public MemberResponse adaptWithProjects(Member source) {
    return new MemberWithProjectsResponse(source.getMemberId().getValue(), source.getLastname(), source.getFirstname(),
                                          source.getLogin(), source.getMemberRole().toString(), source.getMail(),
                                          source.isSubscribed(), source.getProjects().stream().map(ProjectId::getValue)
                                                                       .collect(Collectors.toList()));
  }

  public MemberResponse adaptWithAll(Member source) {
    return new MemberWithAllResponse(source.getMemberId().getValue(), source.getLastname(), source.getFirstname(),
                                     source.getLogin(), source.getMemberRole().toString(), source.getMail(),
                                     source.isSubscribed(),
                                     source.getAddress() instanceof NoAddress ? new AddressResponse()
                                                                              : new AddressResponse(
                                                                                  source.getAddress().city(),
                                                                                  source.getAddress().country(),
                                                                                  source.getAddress().street(),
                                                                                  source.getAddress().zipCode()),
                                     Objects.isNull(source.getProjects()) ? null : source.getProjects().stream()
                                                                                         .map(ProjectId::getValue)
                                                                                         .collect(Collectors.toList()));
  }

}
