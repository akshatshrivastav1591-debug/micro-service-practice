package com.MicoservicePractice.UserService.Service.Client;

import com.MicoservicePractice.UserService.ApiWrapperClass.ApiWrapperClass;
import com.MicoservicePractice.UserService.Dto.ClientDto.PaymentResponseDto;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;
@HttpExchange("/payment")
public interface PaymentClient {
    @GetExchange("/getAllPaymentDetails/{userID}")
     ResponseEntity<ApiWrapperClass<List<PaymentResponseDto>>> getAllPaymentDetails(@NotEmpty(message = "userId Can't be empty") @PathVariable String userID);

}
