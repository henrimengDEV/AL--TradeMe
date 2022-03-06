package org.esgi.core.use_cases.member.domain;

import org.esgi.kernel.annotations.Builder;
import org.esgi.core.use_cases.member.domain.model.Address;
import org.esgi.core.use_cases.member.domain.model.DefaultAddress;

import java.util.Objects;

@Builder
final class AddressBuilder {

    private String city;
    private String street;
    private String country;
    private String zipCode;

    private AddressBuilder() {
    }

    public static AddressBuilder create() {
        return new AddressBuilder();
    }

    public AddressBuilder withCity(String city) {
        AddressBuilder builder = new AddressBuilder();
        builder.city = Objects.requireNonNull(city);
        builder.country = country;
        return builder;
    }

    public AddressBuilder withCountry(String country) {
        AddressBuilder builder = new AddressBuilder();
        builder.city = city;
        builder.country = Objects.requireNonNull(country);
        return builder;
    }

    public AddressBuilder withStreet(String street) {
        AddressBuilder builder = new AddressBuilder();
        builder.city = city;
        builder.country = country;
        builder.street = Objects.requireNonNull(street);
        builder.zipCode = zipCode;
        return builder;
    }

    public AddressBuilder withZipCode(String zipCode) {
        AddressBuilder builder = new AddressBuilder();
        builder.city = city;
        builder.country = country;
        builder.street = street;
        builder.zipCode = Objects.requireNonNull(zipCode);
        return builder;
    }

    public Address build() {
        return DefaultAddress.of(city, country, street, zipCode);
    }
}
