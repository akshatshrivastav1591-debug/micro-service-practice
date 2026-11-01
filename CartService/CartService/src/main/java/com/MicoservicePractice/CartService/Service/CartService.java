package com.MicoservicePractice.CartService.Service;

import com.MicoservicePractice.CartService.ApiWrapperClass.ApiWrapperClass;
import com.MicoservicePractice.CartService.Dto.CartRequestDto;
import com.MicoservicePractice.CartService.Dto.CartResponseDto;
import com.MicoservicePractice.CartService.ExceptionHandeller.CustomExceptions.ConflictResourceException;
import com.MicoservicePractice.CartService.ExceptionHandeller.CustomExceptions.ResourceNotFoundException;
import com.MicoservicePractice.CartService.Mapper.MapperInterface;
import com.MicoservicePractice.CartService.Pojo.CartPojo;
import com.MicoservicePractice.CartService.Repository.CartRepo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartService {
    @Autowired
    CartRepo cartRepo;
    @Autowired
    MapperInterface mapperInterface;
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    private static  final String generalKey="Cart";

    public ResponseEntity<ApiWrapperClass<Void>> createCart(@NotEmpty(message = "UserId Can't be null") String userID) {
        Optional<CartPojo> fetchedCartData=cartRepo.findById(userID);
        if(fetchedCartData.isEmpty()){
            CartPojo newCartEntryObject=new CartPojo();
            newCartEntryObject.setCartID(UUID.randomUUID().toString());
            newCartEntryObject.setUserID(userID);
            newCartEntryObject.setTotalPrice(0.0);
            cartRepo.save(newCartEntryObject);
            return ResponseEntity.status(201).body(new ApiWrapperClass<>("Cart Created",true,null,null));
        }
        else{
            throw new ConflictResourceException("Cart Already Exists:");
        }

    }

    public ResponseEntity<ApiWrapperClass<Void>> addProduct(@Valid CartRequestDto requestDto) {
        CartPojo fetchedCartPojo=cartRepo.findUserCartsAbove(requestDto.getCartID()).orElseThrow(()->new ResourceNotFoundException("Cart not Exists in database"));
        List<String> addedProductList=fetchedCartPojo.getProductList();

        addedProductList.addAll(requestDto.getProductList());
        fetchedCartPojo.setProductList(addedProductList);
        cartRepo.save(fetchedCartPojo);
        redisTemplate.opsForHash().put(getObjectKey(fetchedCartPojo.getCartID()),fetchedCartPojo.getCartID(),fetchedCartPojo);
        return  ResponseEntity.ok(new ApiWrapperClass<>("ProductList Added Successfully",true,null,null)) ;
    }

    public ResponseEntity<ApiWrapperClass<CartResponseDto>> getAddedProductList(@NotEmpty(message = "cartID Can't be null") String cartId) {
        CartPojo cachedCartPoJo=(CartPojo) redisTemplate.opsForHash().get(getObjectKey(cartId),cartId);
        if(cachedCartPoJo!=null){return  ResponseEntity.ok(new ApiWrapperClass<>("Product List Successfully Fetched",true,mapperInterface.CartPojoToCartResponseDto(cachedCartPoJo),null));
        }
        else {
            CartPojo fetchedCartPojo = cartRepo.findUserCartsAbove(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart not Exists in database"));
            redisTemplate.opsForHash().put(getObjectKey(cartId),cartId,fetchedCartPojo);
            return  ResponseEntity.ok(new ApiWrapperClass<>("Product List Successfully Fetched",true,mapperInterface.CartPojoToCartResponseDto(fetchedCartPojo),null));

        }

    }

    public ResponseEntity<ApiWrapperClass<Void>> deleteProductFromCart(@Valid CartRequestDto requestDto){
        CartPojo fetchedCartPojo=cartRepo.findUserCartsAbove(requestDto.getCartID()).orElseThrow(()->new ResourceNotFoundException("Cart not Exists in database"));
        List<String> addedProductList=fetchedCartPojo.getProductList();
        addedProductList.removeAll(requestDto.getProductList());
        fetchedCartPojo.setProductList(addedProductList);
        cartRepo.save(fetchedCartPojo);
        redisTemplate.opsForHash().put(getObjectKey(fetchedCartPojo.getCartID()),fetchedCartPojo.getCartID(),fetchedCartPojo);
        return  ResponseEntity.ok(new ApiWrapperClass<>("ProductList Deleted Successfully",true,null,null));
    }
    private String getObjectKey(String cartId){return generalKey+cartId; }
}
