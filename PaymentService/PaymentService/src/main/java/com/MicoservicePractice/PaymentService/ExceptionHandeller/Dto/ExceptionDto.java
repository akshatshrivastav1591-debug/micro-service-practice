package com.MicoservicePractice.PaymentService.ExceptionHandeller.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
public class ExceptionDto {
    private String exceptionType;
    private Map<String,String> errorMessage;
    private String requestPath;
    private LocalDateTime errorTimeStamp;
}
