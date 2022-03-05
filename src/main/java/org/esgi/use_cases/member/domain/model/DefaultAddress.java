package org.esgi.use_cases.member.domain.model;

public final class DefaultAddress implements Address {

    private final String city;
    private final String street;
    private final String country;
    private final String zipCode;

    private DefaultAddress(String city, String street, String country, String state) {
        this.street = street;
        this.country = country;
        this.zipCode = state;
        this.city = city;
    }

    public static DefaultAddress of(String city, String street, String country, String state) {
        return new DefaultAddress(city, street, country, state);
    }

    @Override
    public String city() {
        return this.city;
    }

    @Override
    public String street() {
        return this.street;
    }

    @Override
    public String country() {
        return this.country;
    }

    @Override
    public String zipCode() {
        return this.zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street = '" + street + '\'' +
                ", city = '" + city + '\'' +
                ", zipcode = '" + zipCode + '\'' +
                ", country = '" + country + '\'' +
                '}';
    }

}
