package com.MicoservicePractice.ProductService.Repository;

import com.MicoservicePractice.ProductService.Pojo.ProductPojo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<ProductPojo,String> {
}
