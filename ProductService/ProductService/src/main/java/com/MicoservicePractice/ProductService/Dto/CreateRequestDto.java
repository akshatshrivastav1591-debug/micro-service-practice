package com.MicoservicePractice.ProductService.Dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CreateRequestDto {
    @NotEmpty(message = "Product Name can't be empty")
    @Size(max = 30,message = "Product Name can't be larger than 30 characters long:")
    private String productName;
    @DecimalMin(value = "0.0",message ="price Should be greater than 0.0" )
    @Digits(integer = 10,fraction = 5,message = "Please choose a value less than  10 integer and upto 5 decimal place")
    private double productPrice;
    @NotEmpty(message = "Product Type can't be empty")
    @Size(max = 15,message = "Product Type can't be larger than 15 characters long:")
    private String productType;

}
