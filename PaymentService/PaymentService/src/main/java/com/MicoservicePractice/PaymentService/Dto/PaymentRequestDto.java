package com.MicoservicePractice.PaymentService.Dto;

import com.MicoservicePractice.PaymentService.DummyData.MethodsOFPayment;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class PaymentRequestDto {
    @NotEmpty(message = "productId Can't be empty")
    private String productID;
    @NotEmpty(message = "UserID Can't be empty")
    private String userID;

    @DecimalMin(value = "0.0",message = "Payment amount should be greater 0.0")
    @Digits(integer = 10,fraction = 5,message = "Payment amount should be less than 10 digits and upto 5 decimal places")
    private double amount;

    private MethodsOFPayment methodOfPayment;
}
