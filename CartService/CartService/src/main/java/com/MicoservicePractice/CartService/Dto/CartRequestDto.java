package com.MicoservicePractice.CartService.Dto;



import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CartRequestDto {
    @NotEmpty(message = "cartID can't be empty")
    private String cartID;
    @NotEmpty(message = "Product List can't be empty")
    private List<String> productList;
}


