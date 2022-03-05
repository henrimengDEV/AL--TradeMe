package org.esgi.use_cases.member.domain.model;


import java.util.Objects;


public class DefaultMember implements Member {
    private final String   lastname;
    private final String   firstname;
    private final String   login;
    private       MemberId memberId;
    private final String   password;
    private       Address    address;
    private final MemberRole memberRole;
    private final String     mail;

    private DefaultMember(String lastname,
                         String firstname,
                         String login,
                         MemberId memberId,
                         String password,
                         Address address,
                         MemberRole memberRole,
                         String mail) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.login = login;
        this.memberId = memberId;
        this.password = password;
        this.address = address;
        this.memberRole = memberRole;
        this.mail = mail;
    }

    public static DefaultMember of(String lastname, String firstname, String login, MemberId memberId, String password,
                                   Address address, MemberRole memberRole, String mail) {
        return new DefaultMember(lastname, firstname, login, memberId, password, address, memberRole, mail);
    }

    @Override
    public String getLastname() {
        return lastname;
    }

    @Override
    public String getFirstname() {
        return firstname;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public MemberId getMemberId() {
        return memberId;
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public MemberRole getMemberRole() {
        return memberRole;
    }

    @Override
    public String getMail() {
        return mail;
    }

    @Override
    public void addMemberId(int id) {
        this.memberId = new MemberId(id);
    }

    @Override
    public void changeAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Member{ " +
                "memberId = '" + (Objects.isNull(memberId) ? "" : memberId.getValue()) + '\'' +
                ", lastname = '" + lastname + '\'' +
                ", firstname = '" + firstname + '\'' +
                ", type = '" + memberRole.getRole() + '\'' +
                ", mail = '" + mail + '\'' +
                '}';
    }

    @Override
    public String toStringWithAddress() {
        return "Member{" +
                "memberId = '" + (Objects.isNull(memberId) ? "" : memberId.getValue()) + '\'' +
                ", lastname = '" + lastname + '\'' +
                ", firstname = '" + firstname + '\'' +
                ", type = '" + memberRole.getRole() + '\'' +
                "\n" + address.toString() +
                ", mail = '" + mail + '\'' +
                '}';
    }


}
