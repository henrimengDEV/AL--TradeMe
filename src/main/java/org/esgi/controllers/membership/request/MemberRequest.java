package org.esgi.controllers.membership.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MemberRequest {

    @NotBlank
    public String lastname;

    @NotBlank
    public String firstname;

    @NotBlank
    public String login;

    @NotBlank
    public String password;

    @NotBlank
    public String memberType;

    @NotNull
    public AddressRequest address;

    @NotBlank
    public String mail;

    @NotNull
    public PaymentRequest payment;
}
