package org.esgi.use_cases.member.application.command;


import org.esgi.kernel.annotations.Service;
import org.esgi.kernel.cqs.CommandHandler;
import org.esgi.kernel.event.ApplicationEvent;
import org.esgi.kernel.event.DomainEvent;
import org.esgi.kernel.event.EventDispatcher;
import org.esgi.use_cases.member.application.PaymentDTO;
import org.esgi.use_cases.member.application.event.ProcessPaymentEvent;
import org.esgi.use_cases.member.domain.AddressFactory;
import org.esgi.use_cases.member.domain.MemberBuilder;
import org.esgi.use_cases.member.domain.MemberRepository;
import org.esgi.use_cases.member.domain.event.memberCreatedEvent;
import org.esgi.use_cases.member.domain.model.Member;
import org.esgi.use_cases.member.domain.model.MemberId;
import org.esgi.use_cases.member.infrastructure.InMemoryMemberRepository;

@Service
public class CreateMemberCommandHandler implements CommandHandler<CreateMember, MemberId> {

    private final org.esgi.use_cases.member.domain.MemberRepository MemberRepository;

    private final EventDispatcher<ApplicationEvent> applicationEventDispatcher;
    private final EventDispatcher<DomainEvent>      domainEventDispatcher;


    public CreateMemberCommandHandler(MemberRepository MemberRepository,
                                      EventDispatcher<ApplicationEvent> applicationEventDispatcher,
                                      EventDispatcher<DomainEvent> domainEventDispatcher) {
        this.MemberRepository = InMemoryMemberRepository.getInstance();
        this.domainEventDispatcher = domainEventDispatcher;
        this.applicationEventDispatcher = applicationEventDispatcher;
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
                                             command.address.zipCode
                                     ))
                                           .withMail(command.mail)
                                           .build();
        MemberRepository.add(member);

        PaymentDTO paymentDTO = new PaymentDTO(command.payment.methodOfPayment, command.payment.subscriptionPlan, command.payment.transactionId);
        applicationEventDispatcher.dispatch(ProcessPaymentEvent.withPayment(memberId, paymentDTO));

        domainEventDispatcher.dispatch(memberCreatedEvent.withMember(member));

        return memberId;
    }
}
