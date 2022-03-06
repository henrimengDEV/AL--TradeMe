package org.esgi.controllers;

import org.esgi.engines.RegulationsEngine;
import org.esgi.shared_kernel.annotations.Controller;
import org.esgi.use_cases.member.application.AddressDTO;
import org.esgi.use_cases.member.application.command.ChangeMemberSubscriptionStatus;
import org.esgi.use_cases.member.application.command.CreateMember;
import org.esgi.use_cases.member.application.query.RetrieveMemberById;
import org.esgi.use_cases.member.application.query.RetrieveMembers;
import org.esgi.use_cases.member.application.query.RetrieveMembersByCity;
import org.esgi.use_cases.member.application.query.RetrieveMembersByRole;
import org.esgi.use_cases.member.domain.model.MemberId;
import org.esgi.use_cases.member.port.MemberAccess;
import org.esgi.use_cases.member.port.request.MemberRequest;
import org.esgi.use_cases.member.port.response.MemberResponse;
import org.esgi.use_cases.member.port.response.MemberWithAddressResponse;
import org.esgi.use_cases.member.port.response.MembersResponse;
import org.esgi.use_cases.payment.application.command.ProcessPayment;
import org.esgi.use_cases.payment.application.query.RetrievePaymentById;
import org.esgi.use_cases.payment.domain.model.PaymentId;
import org.esgi.use_cases.payment.port.PaymentAccess;
import org.esgi.use_cases.payment.port.response.PaymentResponse;
import org.esgi.use_cases.workflows.application.command.ProcessNewMember;
import org.esgi.use_cases.workflows.exposition.WorkflowsAccess;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.logging.Logger;

@Controller
@ApplicationScoped
@Path("/membership")
public class MembershipManager {
    private static final Logger LOGGER = Logger.getLogger(MembershipManager.class.getName());

    private final MemberAccess      memberAccess;
    private final PaymentAccess     paymentAccess;
    private final WorkflowsAccess   workflowsAccess;
    private final RegulationsEngine regulationsEngine;

    public MembershipManager(MemberAccess memberAccess,
                             PaymentAccess paymentAccess,
                             WorkflowsAccess workflowsAccess,
                             RegulationsEngine regulationsEngine) {
        this.memberAccess = memberAccess;
        this.paymentAccess = paymentAccess;
        this.workflowsAccess = workflowsAccess;
        this.regulationsEngine = regulationsEngine;
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/register")
    public Response register(@Valid MemberRequest request) {
        LOGGER.info("Registering Member" + "\n");

        CreateMember createMember = new CreateMember(
                request.lastname,
                request.firstname,
                request.login,
                request.password,
                request.memberType,
                new AddressDTO(
                        request.address.city,
                        request.address.country,
                        request.address.street,
                        request.address.zipcode),
                request.mail
        );
        MemberId memberId = memberAccess.commandBus.send(createMember);

        ProcessPayment processPayment = new ProcessPayment(
                request.payment.methodOfPayment,
                request.payment.subscriptionPlan,
                request.payment.transactionId,
                memberId.getValue()
        );
        PaymentId paymentId = paymentAccess.commandBus.send(processPayment);

        PaymentResponse paymentResponse = paymentAccess.queryBus.send(new RetrievePaymentById(paymentId.getValue()));
        if (paymentResponse.done)
            memberAccess.commandBus.send(new ChangeMemberSubscriptionStatus(memberId.getValue(), true));

        // Regulation engine
        MemberResponse memberResponse = memberAccess.queryBus.send(new RetrieveMemberById(memberId.getValue()));
        regulationsEngine.evaluateAddMember(memberResponse, paymentResponse);

        ProcessNewMember processNewMember = new ProcessNewMember(
                memberId.getValue(),
                paymentId.getValue(),
                memberResponse.mail,
                memberResponse.lastname,
                paymentResponse.price,
                memberResponse.login);
        workflowsAccess.commandBus.send(processNewMember);

        return Response.created(URI.create("/membership/" + memberId.getValue())).build();
    }

    @GET()
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/")
    public Response getByRole(@Valid @NotBlank @QueryParam("role") String role) {
        final MembersResponse membersResponse = memberAccess.queryBus.send(new RetrieveMembersByRole(role));
        return Response.ok(membersResponse).build();
    }

    @GET()
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/all")
    public Response getAll() {
        final MembersResponse membersResponse = memberAccess.queryBus.send(new RetrieveMembers());
        return Response.ok(membersResponse).build();
    }


    @GET()
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{id}")
    public Response getById(@Valid @NotNull @PathParam("id") int id) {
        final MemberWithAddressResponse memberResponse = memberAccess.queryBus.send(new RetrieveMemberById(id));
        return Response.ok(memberResponse).build();
    }

    @GET()
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/city")
    public Response getByCity(@Valid @NotBlank @QueryParam("city") String city) {
        final MembersResponse membersResponse = memberAccess.queryBus.send(new RetrieveMembersByCity(city));
        return Response.ok(membersResponse).build();
    }

}
