package org.esgi.core.engine;

import org.esgi.controllers.market.response.ProjectsResponse;
import org.esgi.controllers.membership.request.CreateMemberRequest;
import org.esgi.controllers.membership.request.PaymentRequest;
import org.esgi.core.use_cases.member.domain.model.Member;
import org.esgi.core.use_cases.payment.domain.model.payment.Payment;
import org.esgi.core.use_cases.project.domain.project.Project;
import org.esgi.core.use_cases.regulation.application.command.CreateMemberRegulation;
import org.esgi.core.use_cases.regulation.application.command.CreateProjectRegulation;
import org.esgi.core.use_cases.regulation.application.command.RegulateUnsubscribedTradesman;
import org.esgi.core.use_cases.regulation.domain.RegulationId;
import org.esgi.core.use_cases.regulation.port.RegulationsAccess;
import org.esgi.kernel.exceptions.RegulatedException;

public class RegulationEngine {

  private final RegulationsAccess regulationsAccess;

  public RegulationEngine(RegulationsAccess regulationsAccess) {
    this.regulationsAccess = regulationsAccess;
  }

  public void auditTradesman(Member member) {
    RegulationId regulationId;
    String regulationDescription = "";
    boolean isRegulated = false;

    if (!member.isSubscribed()) {
      regulationDescription = "Tradesman has no active membership.";
      regulationId = regulationsAccess.commandBus.send(new RegulateUnsubscribedTradesman(
          member.getMemberId().getValue(),
          regulationDescription,
          member.getLogin(),
          member.getMail())
      );
      isRegulated = true;
    }

    if (isRegulated) {
      regulationId = regulationsAccess.commandBus.send(new CreateMemberRegulation(
          member.getMemberId().getValue(),
          regulationDescription)
      );
      throw new RegulatedException(regulationDescription, regulationId.getValue());
    }
  }

  public void verifyCreateMemberRequest(CreateMemberRequest memberRequest) {
    if (memberRequest.lastname.equals("lol")) {
      String regulationDescription = "Lastname can't be a joke. lol.";
      throw new RegulatedException(regulationDescription);
    }
    verifyPaymentRequest(memberRequest.payment);
  }

  private void verifyPaymentRequest(PaymentRequest payment) {
      if (payment.transactionId.length() < 4) {
          throw new IllegalArgumentException(
              "TransactionId: " + payment.transactionId + " is wrong!");
      }
  }

  public void auditProject(Project project) {
    RegulationId regulationId;
    String regulationDescription = "";
    boolean isRegulated = false;

    if (isRegulated) {
      regulationId = regulationsAccess.commandBus.send(new CreateProjectRegulation(
          project.getProjectId().getValue(),
          regulationDescription)
      );
      throw new RegulatedException(
          regulationDescription + " RegulationId=" + regulationId.getValue());
    }
  }

  public void auditPayment(Payment payment, Integer memberId) {
    RegulationId regulationId;
    String regulationDescription = "";
    boolean isRegulated = false;

    if (!payment.isDone()) {
      regulationDescription = "Something went wrong with payment.";
      isRegulated = true;
    }

    if (isRegulated) {
      regulationId = regulationsAccess.commandBus.send(new CreateMemberRegulation(
          memberId,
          regulationDescription)
      );
      throw new RegulatedException(regulationDescription, regulationId.getValue());
    }
  }

  public void auditTradesmanAvailability(ProjectsResponse projectsOfMemberResponse,
                                         Integer contractorProjectId,
                                         Integer memberId) {
    RegulationId regulationId;
    String regulationDescription = "";
    boolean isRegulated = false;

    if (projectsOfMemberResponse.projectResponses.stream().anyMatch(
        project -> project.projectId.equals(contractorProjectId))) {
      regulationDescription = "Tradesman is already in this project.";
      isRegulated = true;
    }

    //todo if sur les dates

    if (isRegulated) {
      regulationId = regulationsAccess.commandBus.send(new CreateMemberRegulation(
          memberId,
          regulationDescription)
      );
      throw new RegulatedException(
          regulationDescription + " RegulationId=" + regulationId.getValue());
    }

  }
}
