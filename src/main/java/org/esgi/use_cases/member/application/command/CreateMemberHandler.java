package org.esgi.use_cases.member.application.command;


import org.esgi.kernel.annotations.Service;
import org.esgi.kernel.cqs.CommandHandler;
import org.esgi.kernel.event.DomainEvent;
import org.esgi.kernel.event.EventDispatcher;
import org.esgi.use_cases.member.domain.AddressFactory;
import org.esgi.use_cases.member.domain.MemberBuilder;
import org.esgi.use_cases.member.domain.MemberRepository;
import org.esgi.use_cases.member.domain.event.memberCreatedEvent;
import org.esgi.use_cases.member.domain.model.Member;
import org.esgi.use_cases.member.domain.model.MemberId;

@Service
public class CreateMemberHandler implements CommandHandler<CreateMember, MemberId> {

    private final org.esgi.use_cases.member.domain.MemberRepository MemberRepository;

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
        MemberRepository.add(member);

        domainEventDispatcher.dispatch(memberCreatedEvent.withMember(member));

        return memberId;
    }
}
