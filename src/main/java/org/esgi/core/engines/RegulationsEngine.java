package org.esgi.core.engines;

import org.esgi.controllers.membership.request.MemberRequest;
import org.esgi.core.use_cases.member.domain.model.Member;
import org.esgi.core.use_cases.payment.domain.model.Payment;
import org.esgi.kernel.exceptions.RegulatedException;

import org.esgi.controllers.market.response.ProjectResponse;
import org.esgi.controllers.market.response.ProjectsResponse;
import org.esgi.core.use_cases.regulations.application.command.CreateMemberRegulation;
import org.esgi.core.use_cases.regulations.application.command.RegulateUnsubscribedTradesman;
import org.esgi.core.use_cases.regulations.domain.RegulationId;
import org.esgi.core.use_cases.regulations.RegulationsAccess;

public class RegulationsEngine {

    private final RegulationsAccess regulationsAccess;

    public RegulationsEngine(RegulationsAccess regulationsAccess) {this.regulationsAccess = regulationsAccess;}

    public void evaluateTradesman(Member member) {
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

        if (isRegulated){
            regulationId = regulationsAccess.commandBus.send(new CreateMemberRegulation(
                    member.getMemberId().getValue(),
                    regulationDescription)
            );
            throw new RegulatedException(regulationDescription, regulationId.getValue());
        }
    }
    public void evaluateMemberRequest(MemberRequest memberRequest) {
        RegulationId regulationId;
        String regulationDescription = "";
        boolean isRegulated = false;

        if(memberRequest.lastname.equals("lol")){
            regulationDescription = "Lastname can't be a joke. lol. Change it soon or your account will be deleted";
            isRegulated = true;
        }

        if (isRegulated){
            throw new RegulatedException(regulationDescription);
        }
    }

    public void evaluateProject(ProjectResponse projectResponse) {
        RegulationId regulationId;
        String regulationDescription = "";
        boolean isRegulated = false;


//        if (isRegulated){
//            regulationId = regulationsAccess.commandBus.send(new CreateProjectRegulation(
//                    projectResponse.projectId,
//                    regulationDescription)
//            );
//            throw new RegulatedException(regulationDescription + " RegulationId=" + regulationId.getValue());
//        }
    }

    public void evaluatePayment(Payment paymentResponse, Integer memberId) {
        RegulationId regulationId;
        String regulationDescription = "";
        boolean isRegulated = false;

        if (!paymentResponse.isDone()){
            regulationDescription = "Something went wrong with payment.";
            isRegulated = true;
        }

        if (isRegulated){
            regulationId = regulationsAccess.commandBus.send(new CreateMemberRegulation(
                    memberId,
                    regulationDescription)
            );
            throw new RegulatedException(regulationDescription, regulationId.getValue());
        }
    }

    public void evaluateTradesmanAvailability(ProjectsResponse projectsOfMemberResponse,
                                              Integer contractorProjectId,
                                              Integer memberId) {
        RegulationId regulationId;
        String regulationDescription = "";
        boolean isRegulated = false;

        if (projectsOfMemberResponse.projectResponses.stream().anyMatch(project -> project.projectId.equals(contractorProjectId))) {
            regulationDescription = "Tradesman is already in this project.";
            isRegulated = true;
        }

        //todo if sur les dates

        if (isRegulated) {
            regulationId = regulationsAccess.commandBus.send(new CreateMemberRegulation(
                    memberId,
                    regulationDescription)
            );
            throw new RegulatedException(regulationDescription + " RegulationId=" + regulationId.getValue());
        }

    }
}
