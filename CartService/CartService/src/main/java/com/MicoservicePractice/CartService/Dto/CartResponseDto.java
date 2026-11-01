package com.MicoservicePractice.CartService.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class CartResponseDto {
    private String cartID;
    private LocalDate lastUpdatedDate;
    private double totalPrice;
    private List<String> productList;
}




