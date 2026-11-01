package com.MicoservicePractice.CartService.Pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@Document(collection = "CartServiceCollection")
public class CartPojo {
    @Id
    private String userID;
    private String cartID;
    @CreatedDate
    private LocalDate createdDate;
    @LastModifiedDate
    private LocalDate lastUpdatedDate;
    private double totalPrice;
    private List<String> productList=new ArrayList<>();
}
