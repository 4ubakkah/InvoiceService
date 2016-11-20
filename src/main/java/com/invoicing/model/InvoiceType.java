package com.invoicing.model;

public enum InvoiceType {

    SHOPPING("ShopPurchase"),
    HOUSING("AdvancePayment");

    private final String value;

    InvoiceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static InvoiceType fromString(String text) {
        if (text != null) {
            for (InvoiceType b : InvoiceType.values()) {
                if (text.equalsIgnoreCase(b.value)) {
                    return b;
                }
            }
        }
        return null;
    }
}
