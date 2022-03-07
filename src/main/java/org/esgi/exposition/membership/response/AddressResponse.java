package org.esgi.exposition.membership.response;

public class AddressResponse {

  public final String city;
  public final String country;
  public final String street;
  public final String zipcode;

  public AddressResponse(String city, String country, String street, String zipcode) {
    this.city = city;
    this.country = country;
    this.street = street;
    this.zipcode = zipcode;
  }

  public AddressResponse() {
    this.city = "Not defined";
    this.country = "Not defined";
    this.street = "Not defined";
    this.zipcode = "Not defined";
  }

  @Override
  public String toString() {
    return "AddressDTO{" + "city='" + city + '\'' + "country='" + country + '\'' + "street='" + street + '\''
        + "zipcode='" + zipcode + '\'' + '}';
  }
}
