package com.MicoservicePractice.UserService.Dto.ClientDto;


import com.MicoservicePractice.UserService.Enum.MethodsOFPayment;
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
