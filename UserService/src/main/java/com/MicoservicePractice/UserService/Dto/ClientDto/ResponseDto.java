package com.MicoservicePractice.UserService.Dto.ClientDto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
public class ResponseDto {
    private String  productId;
    private String productName;
    private double productPrice;
    private String productType;
    private LocalDate launchDate;
}
