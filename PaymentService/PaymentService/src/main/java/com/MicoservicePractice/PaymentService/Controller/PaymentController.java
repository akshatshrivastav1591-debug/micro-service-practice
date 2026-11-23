package com.MicoservicePractice.PaymentService.Controller;
import com.MicoservicePractice.PaymentService.ApiWrapperClass.ApiWrapperClass;
import com.MicoservicePractice.PaymentService.Dto.PaymentRequestDto;
import com.MicoservicePractice.PaymentService.Dto.PaymentResponseDto;
import com.MicoservicePractice.PaymentService.Service.PaymentService;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RefreshScope
@RequestMapping("/payment")
@Validated
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @Value("${testingvalue.value}")
    private String value;

    @PostMapping("/createPaymentRecord")
    public ResponseEntity<ApiWrapperClass<Void>> createPaymentRecord(@Valid @RequestBody PaymentRequestDto paymentRequestDto) {
        return paymentService.createPaymentRecord(paymentRequestDto);
    }

    @GetMapping("/getAllPaymentDetails/{userID}")
    public ResponseEntity<ApiWrapperClass<List<PaymentResponseDto>>> getAllPaymentDetails(@NotEmpty(message = "userId Can't be empty") @PathVariable String userID) {
        return paymentService.getAllPaymentService(userID);
    }

    @GetMapping("/testing")
    public ResponseEntity<ApiWrapperClass<List<PaymentResponseDto>>> testingApi() {
        return ResponseEntity.ok(new ApiWrapperClass<>("Payment Service Working...", true, null, null));
    }

    @Retry(name = "getValue", fallbackMethod = "retryFallBackMethod")
    @GetMapping("/getValue")
    public ResponseEntity<ApiWrapperClass<String>> getValue() {
        System.out.println("Retry-Pattern Testing:");
        throw new ArithmeticException("Retry-Pattern testing");
//        return ResponseEntity.ok(new ApiWrapperClass<>("User Service is working fine:", true, value, null));
    }

    public ResponseEntity<ApiWrapperClass<String>> retryFallBackMethod(Throwable throwable) {
        return ResponseEntity.status(409).body(new ApiWrapperClass<>("retryFallBackMethod Called:", false, "Service-Unavailable please try Again:", null));
    }
}
