package com.MicoservicePractice.ProductService.Dto;

import jakarta.validation.constraints.NotEmpty;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateRequestDto {
    @NotEmpty(message = "ProductId can't be empty")
    private String  productId;
    @Size(message ="Maximum 30 characters allowed" ,max = 30)
    private String productName;
    private double productPrice;
    @Size(message ="Maximum 20 characters allowed" ,max = 20)
    private String productType;
}
