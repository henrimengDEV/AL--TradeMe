package org.esgi.use_cases.member.exposition;


import org.esgi.kernel.cqs.CommandBus;
import org.esgi.kernel.cqs.QueryBus;
import org.esgi.kernel.annotations.Controller;
import org.esgi.use_cases.member.application.AddressDTO;
import org.esgi.use_cases.member.application.PaymentDTO;
import org.esgi.use_cases.member.application.command.CreateUser;
import org.esgi.use_cases.member.application.query.RetrieveUserById;
import org.esgi.use_cases.member.application.query.RetrieveUsers;
import org.esgi.use_cases.member.application.query.RetrieveUsersByCity;
import org.esgi.use_cases.member.domain.model.Member;
import org.esgi.use_cases.member.domain.model.MemberId;
import org.esgi.use_cases.member.exposition.request.MemberRequest;
import org.esgi.use_cases.member.exposition.response.MemberResponse;
import org.esgi.use_cases.member.exposition.response.MemberResponseAdapter;
import org.esgi.use_cases.member.exposition.response.MembersResponse;

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
@Path("/member")
public class MemberController {
    private static final Logger LOGGER = Logger.getLogger(MemberController.class.getName());

    private final CommandBus          commandBus;
    private final QueryBus              queryBus;
    private final MemberResponseAdapter memberResponseAdapter;

    public MemberController(CommandBus commandBus, QueryBus queryBus, MemberResponseAdapter memberResponseAdapter) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
        this.memberResponseAdapter = memberResponseAdapter;

    }

    @GET()
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAll() {
        final List<Member> users = queryBus.send(new RetrieveUsers());
        MembersResponse memberResponseResult = new MembersResponse(
                users.stream().map(memberResponseAdapter::adapt).collect(Collectors.toList())
        );
        return Response.ok(memberResponseResult).build();
    }

    @GET()
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{id}")
    public Response getById(@PathParam("id") int id) {
        final Member   user               = queryBus.send(new RetrieveUserById(id));
        MemberResponse userResponseResult = memberResponseAdapter.adapt(user);
        return Response.ok(userResponseResult).build();
    }

    @GET()
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/city")
    public Response getByCity(@QueryParam("city") String city) {
        final List<Member> users = queryBus.send(new RetrieveUsersByCity(city));
        MembersResponse usersResponseResult = new MembersResponse(
                users.stream().map(memberResponseAdapter::adapt).collect(Collectors.toList())
        );
        return Response.ok(usersResponseResult).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid MemberRequest request) {
        LOGGER.info("Registering User" + "\n");

        CreateUser createUser = new CreateUser(
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
                new PaymentDTO(request.payment.methodOfPayment, request.payment.subscriptionPlan, request.payment.transactionId)
        );

        MemberId userId = commandBus.send(createUser);

        return Response.created(URI.create("/users/" + userId.getValue())).build();
    }

}
