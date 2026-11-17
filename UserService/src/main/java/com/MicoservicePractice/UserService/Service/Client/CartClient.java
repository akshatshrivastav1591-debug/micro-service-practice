package com.MicoservicePractice.UserService.Service.Client;

import com.MicoservicePractice.UserService.ApiWrapperClass.ApiWrapperClass;
import com.MicoservicePractice.UserService.Dto.ClientDto.CartResponseDto;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/cart")
public interface CartClient {
    @GetExchange("/GetProductList/{cartId}")
     ResponseEntity<ApiWrapperClass<CartResponseDto>> getProduct(@NotEmpty(message = "cartID Can't be null") @PathVariable String cartId);

}
