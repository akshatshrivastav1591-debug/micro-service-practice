package com.MicoservicePractice.CartService.Repository;

import com.MicoservicePractice.CartService.Pojo.CartPojo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;


public interface CartRepo extends MongoRepository<CartPojo,String> {
    @Query("{ 'cartID': ?0 }")
    Optional<CartPojo> findUserCartsAbove(String cartID);

}
