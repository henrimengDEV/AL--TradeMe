package org.esgi.core.regulation.application.command;

import org.esgi.core.member.domain.model.MemberId;
import org.esgi.core.regulation.domain.Regulation;
import org.esgi.core.regulation.domain.RegulationId;
import org.esgi.core.regulation.domain.RegulationsRepository;
import org.esgi.core.regulation.domain.event.UnsubscribedMemberEvent;
import org.esgi.core.regulation.domain.MemberRegulation;
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
    Regulation regulation = MemberRegulation.of(regulationId, command.description, MemberId.of(command.memberId));
    Regulation addedRegulation = regulationsRepository.add(regulation);

    domainEventEventDispatcher.dispatch(
        UnsubscribedMemberEvent.create().withMail(command.memberMail).withLogin(command.memberLogin));

    return addedRegulation.getRegulationId();
  }
}
