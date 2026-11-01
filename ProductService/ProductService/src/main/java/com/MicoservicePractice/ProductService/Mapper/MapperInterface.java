package com.MicoservicePractice.ProductService.Mapper;

import com.MicoservicePractice.ProductService.Dto.CreateRequestDto;
import com.MicoservicePractice.ProductService.Dto.ResponseDto;
import com.MicoservicePractice.ProductService.Pojo.ProductPojo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface MapperInterface {
   @Mapping(ignore = true,target = "productId")
   @Mapping(ignore = true,target = "launchDate")
   @Mapping(ignore = true,target = "lastModifiedDate")
   @Mapping(ignore = true,target = "totalSale")
    ProductPojo createRequestDtoToProductPojo(CreateRequestDto createRequestDto);



    ResponseDto productPojoToResponseDto(ProductPojo productPojo);
}

