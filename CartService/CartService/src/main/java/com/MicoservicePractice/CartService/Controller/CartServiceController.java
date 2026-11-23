package com.MicoservicePractice.CartService.Controller;

import com.MicoservicePractice.CartService.ApiWrapperClass.ApiWrapperClass;
import com.MicoservicePractice.CartService.Dto.CartRequestDto;
import com.MicoservicePractice.CartService.Dto.CartResponseDto;
import com.MicoservicePractice.CartService.Service.CartService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@RefreshScope
@RequestMapping("/cart")
@Validated
public class CartServiceController {
 @Autowired
 CartService cartService;
 @Value("${testingvalue.value}")
 private  String value;
@PostMapping("/createCart/{userID}")
 public ResponseEntity<ApiWrapperClass<Void>> createCart(@NotEmpty(message = "UserId Can't be null") @PathVariable String userID) {
    return cartService.createCart(userID);
}
@PutMapping("/AddProduct")
public ResponseEntity<ApiWrapperClass<Void>> addProduct(@Valid @RequestBody CartRequestDto requestDto) {
        return cartService.addProduct(requestDto);
}
@GetMapping("/GetProductList/{cartId}")
public ResponseEntity<ApiWrapperClass<CartResponseDto>> getProduct(@NotEmpty(message = "cartID Can't be null") @PathVariable  String cartId){
        return cartService.getAddedProductList(cartId);
}
@DeleteMapping("/DeleteProduct")
public ResponseEntity<ApiWrapperClass<Void>> deleteProduct(@Valid @RequestBody CartRequestDto requestDto){
        return cartService.deleteProductFromCart(requestDto);
}
@GetMapping("/testing")
public ResponseEntity<ApiWrapperClass<Void>> testingApi(){
        return  ResponseEntity.ok(new ApiWrapperClass<>("Cart Service is Working:",true,null,null));
}
@RateLimiter(name = "getValue",fallbackMethod = "limiterFallbackMethod")
@GetMapping("/getValue")
public ResponseEntity<ApiWrapperClass<String>> getValue() {
    return ResponseEntity.ok(new ApiWrapperClass<>("User Service is working fine:", true, value, null));
}

public ResponseEntity<ApiWrapperClass<String>> limiterFallbackMethod(Throwable throwable){
        System.out.println("getValueFallBack method called:");
        return ResponseEntity.status(429).body(new ApiWrapperClass<>("FallBack Method Called",false,"Rate-Limiting pattern test:",null));
    }

}
