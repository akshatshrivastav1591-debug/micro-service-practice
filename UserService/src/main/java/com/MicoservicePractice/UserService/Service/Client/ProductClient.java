package com.MicoservicePractice.UserService.Service.Client;
import com.MicoservicePractice.UserService.ApiWrapperClass.ApiWrapperClass;
import com.MicoservicePractice.UserService.Dto.ClientDto.ResponseDto;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/Product")
public interface ProductClient {
    @GetExchange("/getProductDetails/{productId}")
    public ResponseEntity<ApiWrapperClass<ResponseDto>> getProductDetails(@NotEmpty @PathVariable String productId);

}
