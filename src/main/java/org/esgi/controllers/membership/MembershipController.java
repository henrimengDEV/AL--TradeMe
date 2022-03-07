package org.esgi.controllers.membership;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.esgi.controllers.membership.request.CreateMemberRequest;
import org.esgi.controllers.membership.response.MemberResponseAdapter;
import org.esgi.controllers.membership.response.MemberWithAddressResponse;
import org.esgi.controllers.membership.response.MembersResponse;
import org.esgi.core.engine.RegulationEngine;
import org.esgi.core.use_cases.member.application.AddressDTO;
import org.esgi.core.use_cases.member.application.command.ChangeMemberSubscriptionStatus;
import org.esgi.core.use_cases.member.application.command.CreateMember;
import org.esgi.core.use_cases.member.application.query.RetrieveMemberById;
import org.esgi.core.use_cases.member.application.query.RetrieveMembers;
import org.esgi.core.use_cases.member.application.query.RetrieveMembersByCity;
import org.esgi.core.use_cases.member.application.query.RetrieveMembersByRole;
import org.esgi.core.use_cases.member.domain.model.Member;
import org.esgi.core.use_cases.member.domain.model.MemberId;
import org.esgi.core.use_cases.member.port.MemberAccess;
import org.esgi.core.use_cases.payment.application.command.ProcessMembershipPayment;
import org.esgi.core.use_cases.payment.application.query.RetrievePaymentById;
import org.esgi.core.use_cases.payment.domain.model.payment.Payment;
import org.esgi.core.use_cases.payment.domain.model.payment.PaymentId;
import org.esgi.core.use_cases.payment.port.PaymentAccess;
import org.esgi.core.use_cases.workflow.application.command.ProcessNewMember;
import org.esgi.core.use_cases.workflow.port.WorkflowsAccess;
import org.esgi.kernel.annotations.Controller;

@Controller
@ApplicationScoped
@Path("/membership")
public class MembershipController {

  private static final Logger LOGGER = Logger.getLogger(MembershipController.class.getName());

  private final MemberAccess    memberAccess;
  private final PaymentAccess   paymentAccess;
  private final WorkflowsAccess workflowsAccess;

  private final RegulationEngine regulationsEngine;

  private final MemberResponseAdapter memberResponseAdapter;

  public MembershipController(MemberAccess memberAccess,
                              PaymentAccess paymentAccess,
                              WorkflowsAccess workflowsAccess,
                              RegulationEngine regulationsEngine) {
    this.memberAccess = memberAccess;
    this.paymentAccess = paymentAccess;
    this.workflowsAccess = workflowsAccess;
    this.regulationsEngine = regulationsEngine;
    this.memberResponseAdapter = new MemberResponseAdapter();
  }

  @POST
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/register")
  public Response register(@Valid CreateMemberRequest request) {
    LOGGER.info("Registering Member" + "\n");

    // evaluate request, if ok then creation, else stop the chain
    regulationsEngine.verifyCreateMemberRequest(request);
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
        request.mail,
        request.geographicZoneOfAvailability,
        request.competences);
    MemberId memberId = memberAccess.commandBus.send(createMember);

    ProcessMembershipPayment processPayment = new ProcessMembershipPayment(
        request.payment.methodOfPayment,
        request.payment.subscriptionPlan,
        request.payment.transactionId,
        memberId.getValue()
    );
    PaymentId paymentId = paymentAccess.commandBus.send(processPayment);

    // evaluate Payment, if ok then update Member subscription status, else notify a problem with payment
    Payment payment = paymentAccess.queryBus.send(new RetrievePaymentById(paymentId.getValue()));
    regulationsEngine.auditPayment(payment, memberId.getValue());
    memberAccess.commandBus.send(new ChangeMemberSubscriptionStatus(memberId.getValue(), true));

    Member member = memberAccess.queryBus.send(new RetrieveMemberById(memberId.getValue()));
    ProcessNewMember processNewMember = new ProcessNewMember(
        memberId.getValue(),
        paymentId.getValue(),
        member.getMail(),
        payment.getPriceToString()
    );
    workflowsAccess.commandBus.send(processNewMember);

    return Response.created(URI.create("/membership/" + memberId.getValue())).build();
  }

  @GET()
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Path("/")
  public Response getByRole(@Valid @NotBlank @QueryParam("role") String role) {
    final List<Member> members = memberAccess.queryBus.send(new RetrieveMembersByRole(role));
    final MembersResponse membersResponse = new MembersResponse(
        members.stream()
               .map(memberResponseAdapter::adaptWithAddress)
               .collect(Collectors.toList())
    );

    return Response.ok(membersResponse).build();
  }

  @GET()
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Path("/all")
  public Response getAll() {
    final List<Member> members = memberAccess.queryBus.send(new RetrieveMembers());
    final MembersResponse membersResponse = new MembersResponse(
        members.stream()
               .map(memberResponseAdapter::adaptWithAddress)
               .collect(Collectors.toList())
    );

    return Response.ok(membersResponse).build();
  }


  @GET()
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Path("/{id}")
  public Response getById(@Valid @NotNull @PathParam("id") int id) {
    final MemberWithAddressResponse memberResponse = memberAccess.queryBus.send(
        new RetrieveMemberById(id));

    return Response.ok(memberResponse).build();
  }

  @GET()
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Path("/city")
  public Response getByCity(@Valid @NotBlank @QueryParam("city") String city) {
    final List<Member> members = memberAccess.queryBus.send(new RetrieveMembersByCity(city));
    final MembersResponse membersResponse = new MembersResponse(
        members.stream()
               .map(memberResponseAdapter::adaptWithAddress)
               .collect(Collectors.toList())
    );

    return Response.ok(membersResponse).build();
  }

}
