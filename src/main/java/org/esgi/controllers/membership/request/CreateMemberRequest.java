package org.esgi.controllers.membership.request;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateMemberRequest {

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

  @NotBlank
  public String geographicZoneOfAvailability;

  @NotEmpty
  public List<String> competences;
}
