package org.esgi;

import org.esgi.kernel.annotations.Controller;
import org.esgi.use_cases.member.application.AddressDTO;
import org.esgi.use_cases.member.application.command.CreateMember;
import org.esgi.use_cases.member.application.query.RetrieveMemberById;
import org.esgi.use_cases.member.application.query.RetrieveMembers;
import org.esgi.use_cases.member.application.query.RetrieveMembersByCity;
import org.esgi.use_cases.member.application.query.RetrieveMembersByRole;
import org.esgi.use_cases.member.domain.model.Member;
import org.esgi.use_cases.member.domain.model.MemberId;
import org.esgi.use_cases.member.exposition.MemberAccess;
import org.esgi.use_cases.member.exposition.request.MemberRequest;
import org.esgi.use_cases.member.exposition.response.MemberResponse;
import org.esgi.use_cases.member.exposition.response.MembersResponse;
import org.esgi.use_cases.payment.application.command.ProcessPayment;
import org.esgi.use_cases.payment.application.query.RetrievePaymentById;
import org.esgi.use_cases.payment.domain.model.Payment;
import org.esgi.use_cases.payment.domain.model.PaymentId;
import org.esgi.use_cases.payment.exposition.PaymentAccess;
import org.esgi.use_cases.workflows.application.command.ProcessNewMember;
import org.esgi.use_cases.workflows.exposition.WorkflowsAccess;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller
@ApplicationScoped
@Path("/membership")
public class MembershipManager {
    private static final Logger          LOGGER = Logger.getLogger(MembershipManager.class.getName());
    private final        MemberAccess    memberAccess;
    private final        PaymentAccess   paymentAccess;
    private final        WorkflowsAccess workflowsAccess;

    public MembershipManager(MemberAccess memberAccess,
                             PaymentAccess paymentAccess,
                             WorkflowsAccess workflowsAccess) {
        this.memberAccess = memberAccess;
        this.paymentAccess = paymentAccess;
        this.workflowsAccess = workflowsAccess;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
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

        Member  member = memberAccess.queryBus.send(new RetrieveMemberById(memberId.getValue()));
        Payment payment = paymentAccess.queryBus.send(new RetrievePaymentById(paymentId.getValue()));
        ProcessNewMember processNewMember = new ProcessNewMember(
                memberId.getValue(),
                paymentId.getValue(),
                member.getMail(),
                member.getLastname(),
                payment.getPrice().getValue() + " " + payment.getPrice().getDevise(),
                member.getLogin());
        workflowsAccess.commandBus.send(processNewMember);

        return Response.created(URI.create("/membership/"+memberId.getValue())).

    build();

}

    @GET()
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/")
    public Response getByRole(@QueryParam("role") String role) {
        final List<Member> members = memberAccess.queryBus.send(new RetrieveMembersByRole(role));
        MembersResponse membersResponse = new MembersResponse(
                members.stream().map(memberAccess.memberResponseAdapter::adapt).collect(Collectors.toList())
        );
        return Response.ok(membersResponse).build();
    }

    @GET()
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/all")
    public Response getAll() {
        final List<Member> members = memberAccess.queryBus.send(new RetrieveMembers());
        MembersResponse memberResponseResult = new MembersResponse(
                members.stream().map(memberAccess.memberResponseAdapter::adapt).collect(Collectors.toList())
        );
        return Response.ok(memberResponseResult).build();
    }


    @GET()
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{id}")
    public Response getById(@PathParam("id") int id) {
        final Member   member               = memberAccess.queryBus.send(new RetrieveMemberById(id));
        MemberResponse memberResponseResult = memberAccess.memberResponseAdapter.adapt(member);
        return Response.ok(memberResponseResult).build();
    }

    @GET()
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/city")
    public Response getByCity(@QueryParam("city") String city) {
        final List<Member> members = memberAccess.queryBus.send(new RetrieveMembersByCity(city));
        MembersResponse membersResponse = new MembersResponse(
                members.stream().map(memberAccess.memberResponseAdapter::adapt).collect(Collectors.toList())
        );
        return Response.ok(membersResponse).build();
    }

}
