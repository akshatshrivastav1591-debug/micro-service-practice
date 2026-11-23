package com.MicoservicePractice.UserService.Service.FallBackClasses;

import com.MicoservicePractice.UserService.ApiWrapperClass.ApiWrapperClass;
import com.MicoservicePractice.UserService.Dto.ClientDto.PaymentResponseDto;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.cloud.client.circuitbreaker.httpservice.HttpServiceFallback;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Component
@HttpServiceFallback(value = PaymentFallBack.class, group = "PaymentService")
public class PaymentFallBack {
   public ResponseEntity<ApiWrapperClass<List<PaymentResponseDto>>> getAllPaymentDetails(@NotEmpty(message = "userId Can't be empty") @PathVariable String userID){
       return  ResponseEntity.status(503).body(new ApiWrapperClass<>("Payment-Service Unavailable",false,null,null));

   }

}
