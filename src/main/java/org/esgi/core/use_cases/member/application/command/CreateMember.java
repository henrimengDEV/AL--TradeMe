package org.esgi.core.use_cases.member.application.command;

import java.util.List;
import org.esgi.core.use_cases.member.application.AddressDTO;
import org.esgi.kernel.cqs.Command;

/**
 * Command object
 */
@SuppressWarnings("all")
public final class CreateMember implements Command {

  public final String       lastname;
  public final String       firstname;
  public final String       login;
  public final String       password;
  public final String       memberRole;
  public final AddressDTO   address;
  public final String       mail;
  public final String       geographicZoneOfAvailability;
  public final List<String> competences;

  public CreateMember(String lastname,
                      String firstname,
                      String login,
                      String password,
                      String memberRole,
                      AddressDTO address,
                      String mail,
                      String geographicZoneOfAvailability,
                      List<String> competences) {
    this.lastname = lastname;
    this.firstname = firstname;
    this.login = login;
    this.password = password;
    this.memberRole = memberRole;
    this.address = address;
    this.mail = mail;
    this.geographicZoneOfAvailability = geographicZoneOfAvailability;
    this.competences = competences;
  }
}
