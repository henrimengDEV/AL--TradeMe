package org.esgi.controllers.membership.response;

@SuppressWarnings("all")
public class MemberResponse {

  public final int     id;
  public final String  lastname;
  public final String  firstname;
  public final String  login;
  public final String  role;
  public final String  mail;
  public final boolean isSubscribed;

  public MemberResponse(int id,
                        String lastname,
                        String firstname,
                        String login,
                        String role,
                        String mail,
                        boolean isSubscribed) {
    this.id = id;
    this.lastname = lastname;
    this.firstname = firstname;
    this.login = login;
    this.role = role;
    this.mail = mail;
    this.isSubscribed = isSubscribed;
  }


  @Override
  public String toString() {
    return "MemberResponse{" +
        "id='" + id + '\'' +
        ", lastname='" + lastname + '\'' +
        ", firstname='" + firstname + '\'' +
        ", login='" + login + '\'' +
        ", memberType='" + role + '\'' +
        ", mail='" + mail + '\'' +
        ", isSubscribed=" + isSubscribed +
        '}';
  }
}
