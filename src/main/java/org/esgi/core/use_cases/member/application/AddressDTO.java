package org.esgi.core.use_cases.member.application;

public class AddressDTO {

  public String city;
  public String street;
  public String country;
  public String zipCode;

  public AddressDTO(String city, String street, String country, String zipCode) {
    this.city = city;
    this.street = street;
    this.country = country;
    this.zipCode = zipCode;
  }
}
