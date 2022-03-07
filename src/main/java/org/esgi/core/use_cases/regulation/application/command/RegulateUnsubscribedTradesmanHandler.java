package org.esgi.core.use_cases.regulation.application.command;

import org.esgi.core.use_cases.member.domain.model.MemberId;
import org.esgi.core.use_cases.regulation.domain.MemberRegulation;
import org.esgi.core.use_cases.regulation.domain.Regulation;
import org.esgi.core.use_cases.regulation.domain.RegulationId;
import org.esgi.core.use_cases.regulation.domain.RegulationsRepository;
import org.esgi.core.use_cases.regulation.domain.event.UnsubscribedMemberEvent;
import org.esgi.kernel.cqs.CommandHandler;
import org.esgi.kernel.event.DomainEvent;
import org.esgi.kernel.event.EventDispatcher;

public class RegulateUnsubscribedTradesmanHandler implements
    CommandHandler<RegulateUnsubscribedTradesman, RegulationId> {

  private final RegulationsRepository        regulationsRepository;
  private final EventDispatcher<DomainEvent> domainEventEventDispatcher;

  public RegulateUnsubscribedTradesmanHandler(RegulationsRepository regulationsRepository,
                                              EventDispatcher<DomainEvent> domainEventEventDispatcher) {
    this.regulationsRepository = regulationsRepository;
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

    domainEventEventDispatcher.dispatch(
        UnsubscribedMemberEvent.create().withMail(command.memberMail)
                               .withLogin(command.memberLogin));

    return addedRegulation.getRegulationId();
  }
}
