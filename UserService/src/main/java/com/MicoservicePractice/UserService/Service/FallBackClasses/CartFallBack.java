package com.MicoservicePractice.UserService.Service.FallBackClasses;

import com.MicoservicePractice.UserService.ApiWrapperClass.ApiWrapperClass;
import com.MicoservicePractice.UserService.Dto.ClientDto.CartResponseDto;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.cloud.client.circuitbreaker.httpservice.HttpServiceFallback;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@HttpServiceFallback(value = CartFallBack.class, group = "CartService")
public class CartFallBack {
   public ResponseEntity<ApiWrapperClass<CartResponseDto>> getProduct(@NotEmpty(message = "cartID Can't be null") @PathVariable String cartId){
       return  ResponseEntity.status(503).body(new ApiWrapperClass<>("Cart-Service Unavailable",false,null,null));
   }


}
