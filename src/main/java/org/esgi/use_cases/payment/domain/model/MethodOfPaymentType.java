package org.esgi.use_cases.payment.domain.model;

public enum MethodOfPaymentType {
    CREDITCARD("credit_card"),
    PAYPAL("paypal");

    private final String paymentType;

    MethodOfPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentType() {
        return this.paymentType;
    }

    public static MethodOfPaymentType fromString(String text) {
        for (MethodOfPaymentType val : MethodOfPaymentType.values()) {
            if (val.paymentType.equalsIgnoreCase(text)) {
                return val;
            }
        }
        return null;
    }


}
