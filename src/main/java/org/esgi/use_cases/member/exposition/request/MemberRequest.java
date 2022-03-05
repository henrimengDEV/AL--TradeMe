package org.esgi.use_cases.member.exposition.request;

import org.esgi.use_cases.payment.exposition.request.PaymentRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MemberRequest {

    @NotNull
    @NotBlank
    public String lastname;

    @NotNull
    @NotBlank
    public String firstname;

    @NotNull
    @NotBlank
    public String login;

    @NotNull
    @NotBlank
    public String password;

    @NotNull
    @NotBlank
    public String memberType;

    @NotNull
    public AddressRequest address;

    @NotNull
    @NotBlank
    public String mail;

    public PaymentRequest payment;
}
