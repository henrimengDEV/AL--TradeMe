package org.esgi.use_cases.member.application.command;


import org.esgi.shared_kernel.annotations.Service;
import org.esgi.shared_kernel.cqs.CommandHandler;
import org.esgi.shared_kernel.event.DomainEvent;
import org.esgi.shared_kernel.event.EventDispatcher;
import org.esgi.use_cases.member.domain.AddressFactory;
import org.esgi.use_cases.member.domain.MemberBuilder;
import org.esgi.use_cases.member.domain.MemberRepository;
import org.esgi.use_cases.member.domain.event.MemberCreatedEvent;
import org.esgi.use_cases.member.domain.model.Member;
import org.esgi.use_cases.member.domain.model.MemberId;

@Service
public class CreateMemberHandler implements CommandHandler<CreateMember, MemberId> {

    private final MemberRepository MemberRepository;

    private final EventDispatcher<DomainEvent>      domainEventDispatcher;


    public CreateMemberHandler(MemberRepository memberRepository,
                               EventDispatcher<DomainEvent> domainEventDispatcher) {
        this.MemberRepository = memberRepository;
        this.domainEventDispatcher = domainEventDispatcher;
    }

    @Override
    public MemberId handle(CreateMember command) {
        final MemberId memberId = MemberRepository.nextId();
        final Member member = MemberBuilder.builder()
                                           .withFirstName(command.firstname)
                                           .withLastName(command.lastname)
                                           .withLogin(command.login)
                                           .withPassword(command.password)
                                           .withMemberId(memberId)
                                           .withMemberRole(command.memberRole)
                                           .withAddress(AddressFactory.create(
                                                   command.address.city,
                                                   command.address.country,
                                                   command.address.street,
                                                   command.address.zipCode))
                                           .withMail(command.mail)
                                           .build();
        Member memberAdded = MemberRepository.add(member);

        domainEventDispatcher.dispatch(
                MemberCreatedEvent.create()
                                  .withLogin(memberAdded.getLogin())
                                  .withMail(memberAdded.getMail())
        );

        return memberAdded.getMemberId();
    }
}
