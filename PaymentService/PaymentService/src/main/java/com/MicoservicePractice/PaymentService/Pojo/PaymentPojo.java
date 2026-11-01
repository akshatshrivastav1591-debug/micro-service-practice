package com.MicoservicePractice.PaymentService.Pojo;

import com.MicoservicePractice.PaymentService.DummyData.MethodsOFPayment;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class PaymentPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String paymentID;
    private String productID;
    private String userID;
    private double amount;
    private boolean status;
    @CreationTimestamp
    private LocalDateTime paymentTimeStamp;
    private MethodsOFPayment methodOfPayment;
}

