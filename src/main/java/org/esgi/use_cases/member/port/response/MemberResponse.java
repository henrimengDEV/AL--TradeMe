package org.esgi.use_cases.member.port.response;

@SuppressWarnings("all")
public class MemberResponse {

    public int          id;
    public String          lastname;
    public String          firstname;
    public String login;
    public String role;
    public String mail;
    public boolean         isSubscribed;
    public AddressResponse address;

    public MemberResponse(int id,
                          String lastname,
                          String firstname,
                          String login,
                          String role,
                          String mail,
                          boolean isSubscribed,
                          AddressResponse address) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.login = login;
        this.role = role;
        this.mail = mail;
        this.isSubscribed = isSubscribed;
        this.address = address;
    }


    @Override public String toString() {
        return "MemberResponse{" +
                "id='" + id + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", login='" + login + '\'' +
                ", memberType='" + role + '\'' +
                ", mail='" + mail + '\'' +
                ", isSubscribed=" + isSubscribed +
                ", address=" + address +
                '}';
    }
}
