package com.MicoservicePractice.CartService.Mapper;


import com.MicoservicePractice.CartService.Dto.CartResponseDto;
import com.MicoservicePractice.CartService.Pojo.CartPojo;
import org.mapstruct.Mapper;




@Mapper(componentModel = "spring")
public interface MapperInterface {
 CartResponseDto CartPojoToCartResponseDto(CartPojo cartPojo);
}
