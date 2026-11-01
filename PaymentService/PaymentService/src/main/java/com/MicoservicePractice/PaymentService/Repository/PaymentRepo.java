package com.MicoservicePractice.PaymentService.Repository;

import com.MicoservicePractice.PaymentService.Pojo.PaymentPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepo extends JpaRepository<PaymentPojo,String> {
    @Query("select m from PaymentPojo m where m.userID = :userId ORDER BY m.paymentTimeStamp DESC limit 50")
    List<PaymentPojo> findBYUserID(String userId);
}
