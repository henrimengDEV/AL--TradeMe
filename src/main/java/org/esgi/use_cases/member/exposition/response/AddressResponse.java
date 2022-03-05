package org.esgi.use_cases.member.exposition.response;

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

    @Override
    public String toString() {
        return "AddressDTO{" +
                "city='" + city + '\'' +
                "country='" + country + '\'' +
                "street='" + street + '\'' +
                "zipcode='" + zipcode + '\'' +
                '}';
    }
}
