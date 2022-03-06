package org.esgi.engines;

import org.esgi.shared_kernel.exceptions.RegulatedException;
import org.esgi.use_cases.member.domain.model.MemberRole;
import org.esgi.use_cases.member.port.response.MemberResponse;
import org.esgi.use_cases.payment.exposition.response.PaymentResponse;
import org.esgi.use_cases.projects.domain.Project;
import org.esgi.use_cases.regulations.application.command.CreateMemberRegulation;
import org.esgi.use_cases.regulations.application.command.RegulateUnsubscribedTradesman;
import org.esgi.use_cases.regulations.domain.RegulationId;
import org.esgi.use_cases.regulations.exposition.RegulationsAccess;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RegulationsEngine {

    private final RegulationsAccess regulationsAccess;

    public RegulationsEngine(RegulationsAccess regulationsAccess) {this.regulationsAccess = regulationsAccess;}


    void evaluateAddMember(MemberResponse member, PaymentResponse paymentResponse) {
        RegulationId regulationId;
        String regulationDescription = "";
        boolean isRegulated = false;

        if (!member.isSubscribed){
            regulationDescription = "Something went wrong with payment.";
            isRegulated = true;
        }

        if (isRegulated){
            regulationId = regulationsAccess.commandBus.send(new CreateMemberRegulation(
                    member.id,
                    regulationDescription)
            );
            throw new RegulatedException(regulationDescription + " RegulationId=" + regulationId);
        }
    }
    void evaluateRequestTradesman(MemberResponse member, Project project) {
        RegulationId regulationId;
        String regulationDescription = "";
        boolean isRegulated = false;

        if (!member.isSubscribed) {
            regulationDescription = "Tradesman has no active membership when requested by contractor.";
            regulationId = regulationsAccess.commandBus.send(new RegulateUnsubscribedTradesman(
                    member.id,
                    regulationDescription,
                    member.login,
                    member.mail)
            );
            isRegulated = true;
        }

        if (!member.role.equals(MemberRole.TRADESMAN.getValue())) {
            regulationDescription = "The requested member isn't a Tradesman.";
            isRegulated = true;
        }

        if (project.getParticipants().contains(member.id)) {
            regulationDescription = "Tradesman is already in this project.";
            isRegulated = true;
        }

        if (isRegulated) {
            regulationId = regulationsAccess.commandBus.send(new CreateMemberRegulation(
                    member.id,
                    regulationDescription)
            );
            throw new RegulatedException(regulationDescription + " RegulationId=" + regulationId);
        }

    }
}
