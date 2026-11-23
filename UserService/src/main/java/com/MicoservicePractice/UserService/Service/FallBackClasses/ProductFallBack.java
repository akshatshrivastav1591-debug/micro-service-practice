package com.MicoservicePractice.UserService.Service.FallBackClasses;

import com.MicoservicePractice.UserService.ApiWrapperClass.ApiWrapperClass;

import com.MicoservicePractice.UserService.Dto.ClientDto.ResponseDto;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.cloud.client.circuitbreaker.httpservice.HttpServiceFallback;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@HttpServiceFallback(value = ProductFallBack.class, group = "ProductService")
public class ProductFallBack {
    public ResponseEntity<ApiWrapperClass<ResponseDto>> getProductDetails(@NotEmpty @PathVariable String productId){
        return  ResponseEntity.status(503).body(new ApiWrapperClass<>("Product-Service Unavailable",false,null,null));

    }

}
