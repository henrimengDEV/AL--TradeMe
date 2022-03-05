package org.esgi.use_cases.member.exposition.response;

@SuppressWarnings("all")
public class MemberResponse {

    public String          id;
    public String          lastname;
    public String          firstname;
    public String          login;
    public String          memberType;
    public String          mail;
    public AddressResponse address;

    public MemberResponse(String id, String lastname, String firstname, String login, String memberType, String mail,
                          AddressResponse address) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.login = login;
        this.memberType = memberType;
        this.mail = mail;
        this.address = address;
    }


    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", login='" + login + '\'' +
                ", mail='" + mail + '\'' +
                ", memberType='" + memberType + '\'' +
                ", address=" + address +
                '}';
    }
}
