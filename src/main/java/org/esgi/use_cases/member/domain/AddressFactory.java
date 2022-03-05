package org.esgi.use_cases.member.domain;


import org.esgi.kernel.annotations.Pattern;
import org.esgi.use_cases.member.domain.model.Address;
import org.esgi.use_cases.member.domain.model.NoAddress;

@Pattern
public final class AddressFactory {

    private AddressFactory() {
        throw new AssertionError();
    }

    public static Address create(String city, String country, String street, String zipCode) {
        Address address = AddressBuilder.create()
                                                                                                                        .withCity(city)
                                                                                                                        .withCountry(country)
                                                                                                                        .withStreet(street)
                                                                                                                        .withZipCode(zipCode)
                                                                                                                        .build();
        if (AddressValidator.getInstance().test(address))
            return address;

        return new NoAddress();
    }

    // Exemple
    public static Address create(String country) {
        return AddressBuilder.create()
                                                                     .withCountry(country)
                                                                     .build();
    }

    public static Address create() {
        return new NoAddress();
    }
}
