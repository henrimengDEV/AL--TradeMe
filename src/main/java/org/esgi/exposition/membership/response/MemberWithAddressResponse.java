package org.esgi.exposition.membership.response;

@SuppressWarnings("all")
public class MemberWithAddressResponse extends MemberResponse {

  public final AddressResponse address;

  public MemberWithAddressResponse(int id, String lastname, String firstname, String login, String role, String mail,
                                   boolean isSubscribed, AddressResponse address) {
    super(id, lastname, firstname, login, role, mail, isSubscribed);
    this.address = address;
  }


  @Override
  public String toString() {
    return "MemberResponse{" + "id='" + id + '\'' + ", lastname='" + lastname + '\'' + ", firstname='" + firstname
        + '\'' + ", login='" + login + '\'' + ", memberType='" + role + '\'' + ", mail='" + mail + '\''
        + ", isSubscribed=" + isSubscribed + ", address=" + address + '}';
  }
}
