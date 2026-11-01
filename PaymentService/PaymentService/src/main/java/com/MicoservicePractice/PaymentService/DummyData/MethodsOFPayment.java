package com.MicoservicePractice.PaymentService.DummyData;

import lombok.Getter;

@Getter
public enum MethodsOFPayment {
    CREDIT_CARD("Credit Card"),
    DEBIT_CARD("Debit Card"),
    UPI("UPI"),
    NET_BANKING("Net Banking"),
    WALLET("Wallet"),
    CASH_ON_DELIVERY("Cash on Delivery"),
    EMI("EMI");
    private final String displayName;

    MethodsOFPayment(String displayName) {
        this.displayName=displayName;
    }
}
