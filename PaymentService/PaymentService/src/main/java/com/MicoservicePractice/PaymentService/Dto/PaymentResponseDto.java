package com.MicoservicePractice.PaymentService.Dto;

import com.MicoservicePractice.PaymentService.DummyData.MethodsOFPayment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PaymentResponseDto {
    private String productID;
    private String userID;
    private double amount;
    private boolean status;
    private LocalDateTime paymentTimeStamp;
    private MethodsOFPayment methodOfPayment;
}
