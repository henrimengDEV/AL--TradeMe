package org.esgi.use_cases.regulations.application.command;

import org.esgi.shared_kernel.cqs.CommandHandler;
import org.esgi.shared_kernel.event.DomainEvent;
import org.esgi.shared_kernel.event.EventDispatcher;
import org.esgi.use_cases.member.domain.model.MemberId;
import org.esgi.use_cases.regulations.domain.MemberRegulation;
import org.esgi.use_cases.regulations.domain.Regulation;
import org.esgi.use_cases.regulations.domain.RegulationId;
import org.esgi.use_cases.regulations.domain.RegulationsRepository;
import org.esgi.use_cases.regulations.domain.event.UnsubscribedMemberEvent;

public class RegulateUnsubscribedTradesmanHandler implements CommandHandler<RegulateUnsubscribedTradesman, RegulationId> {

    private final RegulationsRepository regulationsRepository;
    private final EventDispatcher<DomainEvent> domainEventEventDispatcher;

    public RegulateUnsubscribedTradesmanHandler(RegulationsRepository regulationsRepository,
                                                EventDispatcher<DomainEvent> domainEventEventDispatcher) {this.regulationsRepository = regulationsRepository;
        this.domainEventEventDispatcher = domainEventEventDispatcher;
    }


    @Override
    public RegulationId handle(RegulateUnsubscribedTradesman command) {

        RegulationId regulationId = regulationsRepository.nextId();
        Regulation regulation = MemberRegulation.of(
                regulationId,
                command.description,
                MemberId.of(command.memberId)
        );
        Regulation addedRegulation = regulationsRepository.add(regulation);

        domainEventEventDispatcher.dispatch(UnsubscribedMemberEvent.create().withMail(command.memberMail).withLogin(command.memberLogin));

        return addedRegulation.getRegulationId();
    }
}
