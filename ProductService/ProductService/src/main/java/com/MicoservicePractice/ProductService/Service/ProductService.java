package com.MicoservicePractice.ProductService.Service;
import com.MicoservicePractice.ProductService.ApiWrapperClass.ApiWrapperClass;
import com.MicoservicePractice.ProductService.Dto.CreateRequestDto;
import com.MicoservicePractice.ProductService.Dto.ResponseDto;
import com.MicoservicePractice.ProductService.Dto.UpdateRequestDto;
import com.MicoservicePractice.ProductService.ExceptionHandeller.CustomExceptions.ResourceNotFoundException;
import com.MicoservicePractice.ProductService.Mapper.MapperInterface;
import com.MicoservicePractice.ProductService.Pojo.ProductPojo;
import com.MicoservicePractice.ProductService.Repository.ProductRepo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;


@Service
@Slf4j
public class ProductService {
    @Autowired
    ProductRepo repo;
    @Autowired
    MapperInterface mapperInterface;
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    private  static  final String generalKey="product";
    public ResponseEntity<ApiWrapperClass<Void>> createNewProductEntry(@Valid CreateRequestDto createRequestDto)
    {
        System.out.println("Create Service method Called");
        ProductPojo productPojo=mapperInterface.createRequestDtoToProductPojo(createRequestDto);

        productPojo.setTotalSale(0);
         repo.save(productPojo);
        return  ResponseEntity.status(201).body(new ApiWrapperClass<>("Product entry was created",true,null,null));
    }

    public ResponseEntity<ApiWrapperClass<ResponseDto>> getProductDetails(@NotEmpty String productId) {

        ProductPojo cachedProductDetails=(ProductPojo) redisTemplate.opsForHash().get(getObjectKey(productId),productId);
        if(cachedProductDetails!=null){
            return ResponseEntity.ok(new ApiWrapperClass<>("Product Details Fetched Successfully",true,mapperInterface.productPojoToResponseDto(cachedProductDetails),null));
        }
        else {
            ProductPojo fetchedProductDetails = repo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product Not Found in database"));
            redisTemplate.opsForHash().put(getObjectKey(productId),productId,fetchedProductDetails);
            redisTemplate.expire(getObjectKey(productId), Duration.ofMinutes(10));
            return ResponseEntity.ok(new ApiWrapperClass<>("Product Details Fetched Successfully",true,mapperInterface.productPojoToResponseDto(fetchedProductDetails),null));

        }
    }

    public ResponseEntity<ApiWrapperClass<Void>> updateProductDetails(@Valid  UpdateRequestDto updatedProductDetails) {
        boolean hasUpdatedProductName = updatedProductDetails.getProductName() != null
                && !updatedProductDetails.getProductName().isBlank();
        boolean hasUpdatedProductType= updatedProductDetails.getProductType() != null
                && !updatedProductDetails.getProductType().isBlank();
        Double updatedProductPrice= updatedProductDetails.getProductPrice();

        ProductPojo fetchedProductDetails=repo.findById(updatedProductDetails.getProductId()).orElseThrow(()-> new ResourceNotFoundException("Product Not Found in database"));
        if((updatedProductPrice != null && !updatedProductPrice.equals(0.0)) && (hasUpdatedProductName && hasUpdatedProductType)){
            fetchedProductDetails.setProductName(updatedProductDetails.getProductName());
            fetchedProductDetails.setProductType(updatedProductDetails.getProductType());
            fetchedProductDetails.setProductPrice(updatedProductPrice);
        }
        if(hasUpdatedProductName) fetchedProductDetails.setProductName(updatedProductDetails.getProductName());
        if(hasUpdatedProductType) fetchedProductDetails.setProductType(updatedProductDetails.getProductType());
        if(updatedProductPrice!=null && !updatedProductPrice.equals(0.0)) fetchedProductDetails.setProductPrice(updatedProductPrice);
        repo.save(fetchedProductDetails);
        redisTemplate.opsForHash().put(getObjectKey(fetchedProductDetails.getProductId()),fetchedProductDetails.getProductId(),fetchedProductDetails);
        redisTemplate.expire(getObjectKey(fetchedProductDetails.getProductId()),Duration.ofMinutes(10));
        return ResponseEntity.ok(new ApiWrapperClass<>("Product Details Updated Successfully",true,null,null));
    }

    public ResponseEntity<ApiWrapperClass<Void>> deleteProductDetails(@NotEmpty String productId) {
        repo.deleteById(productId);
        redisTemplate.opsForHash().delete(getObjectKey(productId),productId);
        return  ResponseEntity.ok(new ApiWrapperClass<>("Product Details Deleted Successfully",true,null,null));
    }
    private String getObjectKey(String productKey) {return generalKey+productKey;}


}
