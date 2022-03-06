package org.esgi.engines;

import org.esgi.shared_kernel.exceptions.RegulatedException;
import org.esgi.use_cases.member.port.response.MemberResponse;
import org.esgi.use_cases.payment.port.response.PaymentResponse;
import org.esgi.use_cases.projects.port.response.ProjectsResponse;
import org.esgi.use_cases.regulations.application.command.CreateMemberRegulation;
import org.esgi.use_cases.regulations.application.command.RegulateUnsubscribedTradesman;
import org.esgi.use_cases.regulations.domain.RegulationId;
import org.esgi.use_cases.regulations.port.RegulationsAccess;

public class RegulationsEngine {

    private final RegulationsAccess regulationsAccess;

    public RegulationsEngine(RegulationsAccess regulationsAccess) {this.regulationsAccess = regulationsAccess;}

    public void evaluateAddMember(MemberResponse member, PaymentResponse paymentResponse) {
        RegulationId regulationId;
        String regulationDescription = "";
        boolean isRegulated = false;

        if (!member.isSubscribed) {
            regulationDescription = "Tradesman has no active membership.";
            regulationId = regulationsAccess.commandBus.send(new RegulateUnsubscribedTradesman(
                    member.id,
                    regulationDescription,
                    member.login,
                    member.mail)
            );
            isRegulated = true;
        }

        if (!paymentResponse.done){
            regulationDescription = "Something went wrong with payment.";
            isRegulated = true;
        }

        if(member.lastname.equals("lol")){
            regulationDescription = "Your lastname can't be a joke. lol.";
            isRegulated = true;
        }

        if (isRegulated){
            regulationId = regulationsAccess.commandBus.send(new CreateMemberRegulation(
                    member.id,
                    regulationDescription)
            );
            throw new RegulatedException(regulationDescription + " RegulationId=" + regulationId.getValue());
        }
    }
    public void evaluateRequestTradesman(ProjectsResponse projectsOfMemberResponse,
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
