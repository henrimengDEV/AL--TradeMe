package org.esgi.use_cases.member.application.command;

import org.esgi.kernel.cqs.Command;
import org.esgi.use_cases.member.application.AddressDTO;
import org.esgi.use_cases.member.application.PaymentDTO;

/**
 * Command object
 */
@SuppressWarnings("all")
public final class CreateUser implements Command {

    public final String     lastname;
    public final String     firstname;
    public final String     login;
    public final String     password;
    public final String     userType;
    public final AddressDTO address;
    public final String     mail;
    public final PaymentDTO payment;

    public CreateUser(String lastname,
                      String firstname,
                      String login,
                      String password,
                      String userType,
                      AddressDTO address,
                      String mail,
                      PaymentDTO payment) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.login = login;
        this.password = password;
        this.userType = userType;
        this.address = address;
        this.mail = mail;
        this.payment = payment;
    }
}
