package org.esgi.controllers.membership.response;

import java.util.List;

@SuppressWarnings("all")
public class MemberWithProjectsResponse extends MemberResponse{


    public final List<Integer> projectsId;

    public MemberWithProjectsResponse(int id,
                                      String lastname,
                                      String firstname,
                                      String login,
                                      String role,
                                      String mail,
                                      boolean isSubscribed,
                                      List<Integer> projectsId) {
        super(id, lastname, firstname, login, role, mail, isSubscribed);
        this.projectsId = projectsId;
    }

    @Override public String toString() {
        return "MemberWithProjectsResponse{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", login='" + login + '\'' +
                ", role='" + role + '\'' +
                ", mail='" + mail + '\'' +
                ", isSubscribed=" + isSubscribed +
                ", projectsId=" + projectsId +
                '}';
    }
}
