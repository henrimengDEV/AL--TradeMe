package org.esgi.core.use_cases.member.application.command;


import java.util.List;
import java.util.stream.Collectors;
import org.esgi.core.use_cases.member.domain.AddressFactory;
import org.esgi.core.use_cases.member.domain.GeographicZone;
import org.esgi.core.use_cases.member.domain.MemberBuilder;
import org.esgi.core.use_cases.member.domain.MemberRepository;
import org.esgi.core.use_cases.member.domain.event.MemberCreatedEvent;
import org.esgi.core.use_cases.member.domain.model.Member;
import org.esgi.core.use_cases.member.domain.model.MemberId;
import org.esgi.core.use_cases.project.domain.job.JobType;
import org.esgi.kernel.annotations.Service;
import org.esgi.kernel.cqs.CommandHandler;
import org.esgi.kernel.event.DomainEvent;
import org.esgi.kernel.event.EventDispatcher;

@Service
public class CreateMemberHandler implements CommandHandler<CreateMember, MemberId> {

  private final MemberRepository memberRepository;

  private final EventDispatcher<DomainEvent> domainEventDispatcher;


  public CreateMemberHandler(MemberRepository memberRepository,
                             EventDispatcher<DomainEvent> domainEventDispatcher) {
    this.memberRepository = memberRepository;
    this.domainEventDispatcher = domainEventDispatcher;
  }

  @Override
  public MemberId handle(CreateMember command) {
    final MemberId memberId = memberRepository.nextId();

    final List<JobType> competencesList = command.competences.stream()
                                                             .map(JobType::fromString)
                                                             .collect(Collectors.toList());

    //to regulation
      if (competencesList.isEmpty()) {
          throw new IllegalArgumentException("At least one competence is required");
      }

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
                                       .withGeographicZoneOfAvailability(GeographicZone.fromString(
                                           command.geographicZoneOfAvailability))
                                       .withCompetences(competencesList)
                                       .build();
    Member memberAdded = memberRepository.add(member);

    domainEventDispatcher.dispatch(
        MemberCreatedEvent.create()
                          .withLogin(memberAdded.getLogin())
                          .withMail(memberAdded.getMail())
    );

    return memberAdded.getMemberId();
  }
}
