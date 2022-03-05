package org.esgi.use_cases.member.application.command;

import org.esgi.kernel.cqs.Command;
import org.esgi.use_cases.member.application.AddressDTO;

/**
 * Command object
 */
@SuppressWarnings("all")
public final class CreateMember implements Command {

    public final String     lastname;
    public final String     firstname;
    public final String     login;
    public final String     password;
    public final String     memberRole;
    public final AddressDTO address;
    public final String     mail;

    public CreateMember(String lastname,
                        String firstname,
                        String login,
                        String password,
                        String memberRole,
                        AddressDTO address,
                        String mail) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.login = login;
        this.password = password;
        this.memberRole = memberRole;
        this.address = address;
        this.mail = mail;
    }
}
