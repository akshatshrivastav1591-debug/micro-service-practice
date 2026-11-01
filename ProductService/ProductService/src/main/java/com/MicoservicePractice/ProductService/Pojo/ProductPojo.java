package com.MicoservicePractice.ProductService.Pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@Document(collection = "ProductServiceCollection")
public class ProductPojo {
    @Id
    private String  productId;
    private String productName;
    private double productPrice;
    private String productType;
    @CreatedDate
    private LocalDate launchDate;
    @LastModifiedDate
    private LocalDate lastModifiedDate;
    private int totalSale;
}
