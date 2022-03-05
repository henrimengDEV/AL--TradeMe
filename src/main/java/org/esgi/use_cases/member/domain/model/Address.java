package org.esgi.use_cases.member.domain.model;

public interface Address {
    String city();
    String street();
    String country();
    String zipCode();
    String toString();
}