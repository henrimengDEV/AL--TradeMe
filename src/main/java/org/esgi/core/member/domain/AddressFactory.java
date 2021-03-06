package org.esgi.core.member.domain;


import org.esgi.core.member.domain.model.Address;
import org.esgi.core.member.domain.model.NoAddress;
import org.esgi.kernel.annotations.Pattern;

@Pattern
public final class AddressFactory {

  private AddressFactory() {
    throw new AssertionError();
  }

  public static Address create(String city, String country, String street, String zipCode) {
    if (city.isBlank() && country.isBlank() && street.isBlank() && zipCode.isBlank()) {
      return new NoAddress();
    } else {
      Address address = AddressBuilder.create().withCity(city).withCountry(country).withStreet(street)
                                      .withZipCode(zipCode).build();
      if (AddressValidator.getInstance().test(address)) {
        return address;
      }

      return new NoAddress();
    }
  }

  // Exemple
  public static Address create(String country) {
    return AddressBuilder.create().withCountry(country).build();
  }

  public static Address create() {
    return new NoAddress();
  }
}
