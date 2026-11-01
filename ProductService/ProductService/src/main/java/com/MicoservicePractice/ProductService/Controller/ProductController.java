package com.MicoservicePractice.ProductService.Controller;

import com.MicoservicePractice.ProductService.ApiWrapperClass.ApiWrapperClass;
import com.MicoservicePractice.ProductService.Dto.CreateRequestDto;
import com.MicoservicePractice.ProductService.Dto.ResponseDto;
import com.MicoservicePractice.ProductService.Dto.UpdateRequestDto;
import com.MicoservicePractice.ProductService.Service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Product")
@RefreshScope
@Validated
@Slf4j
public class ProductController {
    @Autowired
    ProductService productService;
    @Value("${testingvalue.value}")
    private  String value;
    @PostMapping("/createProductEntry")
    public ResponseEntity<ApiWrapperClass<Void>> createProductEntry(@Valid @RequestBody CreateRequestDto createRequestDto){
        System.out.println("Create Controller method Called");
        return  productService.createNewProductEntry(createRequestDto);
    }

    @GetMapping("/getProductDetails/{productId}")
    public ResponseEntity<ApiWrapperClass<ResponseDto>> getProductDetails(@NotEmpty @PathVariable String productId){
        return  productService.getProductDetails(productId);
    }

    @PutMapping("/updateProductDetails")
    public ResponseEntity<ApiWrapperClass<Void>>  updateProductDetails(@Valid @RequestBody UpdateRequestDto updatedProductDetails){
        return  productService.updateProductDetails(updatedProductDetails);
    }

    @DeleteMapping ("/deleteProductRecord/{productId}")
    public ResponseEntity<ApiWrapperClass<Void>> deleteProductDetails(@NotEmpty @PathVariable String productId){
        return  productService.deleteProductDetails(productId);
    }

    @GetMapping("/testing")
    public ResponseEntity<ApiWrapperClass<Void>> testingApi(){
        return  ResponseEntity.ok(new ApiWrapperClass<>("Product Service is Working:",true,null,null));
    }
    @GetMapping("/getValue")
    public ResponseEntity<ApiWrapperClass<String>> getValue() {
        return ResponseEntity.ok(new ApiWrapperClass<>("User Service is working fine:", true, value, null));
    }
}
