package org.esgi.use_cases.member.port.response;

import java.util.List;

@SuppressWarnings("all")
public class MemberWithAllResponse extends MemberResponse{

    public final AddressResponse address;
    public final List<Integer>   projectsId;
    public MemberWithAllResponse(int id,
                                 String lastname,
                                 String firstname,
                                 String login,
                                 String role,
                                 String mail,
                                 boolean isSubscribed,
                                 AddressResponse address,
                                 List<Integer> projectsId) {
        super(id, lastname, firstname, login, role, mail, isSubscribed);
        this.address = address;
        this.projectsId = projectsId;
    }


    @Override public String toString() {
        return "MemberWithAllResponse{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", login='" + login + '\'' +
                ", role='" + role + '\'' +
                ", mail='" + mail + '\'' +
                ", isSubscribed=" + isSubscribed +
                ", address=" + address +
                ", projectsId=" + projectsId +
                '}';
    }
}
