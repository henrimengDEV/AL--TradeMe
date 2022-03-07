package org.esgi.core.member.domain;

public enum GeographicZone {
  CITY("city"), REGION("region"), COUNTRY("country");

  private final String zone;

  GeographicZone(String zone) {
    this.zone = zone;
  }

  public static GeographicZone fromString(String text) {
    for (GeographicZone val : GeographicZone.values()) {
      if (val.zone.equalsIgnoreCase(text)) {
        return val;
      }
    }
    return null;
  }

  public String getValue() {
    return this.zone;
  }
}
