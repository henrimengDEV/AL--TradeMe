package org.esgi.core.payment.domain.model.price;

public enum DeviseType {
  EURO("euro"), DOLLAR("dollar"), NO_DEVISE("no_devise");

  private final String deviseType;

  DeviseType(String deviseType) {
    this.deviseType = deviseType;
  }

  public static DeviseType fromString(String text) {
    for (DeviseType val : DeviseType.values()) {
      if (val.deviseType.equalsIgnoreCase(text)) {
        return val;
      }
    }
    return null;
  }

  public String getValue() {
    return this.deviseType;
  }


}
