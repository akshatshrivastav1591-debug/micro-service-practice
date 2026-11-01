package com.MicoservicePractice.PaymentService.Service;

import com.MicoservicePractice.PaymentService.ApiWrapperClass.ApiWrapperClass;
import com.MicoservicePractice.PaymentService.Dto.PaymentRequestDto;
import com.MicoservicePractice.PaymentService.Dto.PaymentResponseDto;
import com.MicoservicePractice.PaymentService.Mapper.MapperInterface;
import com.MicoservicePractice.PaymentService.Pojo.PaymentPojo;
import com.MicoservicePractice.PaymentService.Repository.PaymentRepo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {
@Autowired
 PaymentRepo paymentRepo;
@Autowired
 MapperInterface mapperInterface;
@Autowired
 RedisTemplate<String,Object> redisTemplate;
private static final String generalKey="Payment";


 public ResponseEntity<ApiWrapperClass<Void>> createPaymentRecord(@Valid PaymentRequestDto paymentRequestDto) {
  PaymentPojo paymentPojo=mapperInterface.paymentRequestDtoToPaymentPojo(paymentRequestDto);
  paymentPojo.setStatus(true);
  paymentRepo.save(paymentPojo);
  return ResponseEntity.status(201).body(new ApiWrapperClass<>("Payment Recorded Successfully:",true,null,null));
 }

 public ResponseEntity<ApiWrapperClass<List<PaymentResponseDto>>> getAllPaymentService(@NotEmpty(message = "userId Can't be empty") String userID) {
  List<PaymentPojo> fetchedPaymentDetails=paymentRepo.findBYUserID(userID);
  List<PaymentResponseDto> paymentResponseDtos=new ArrayList<>();
  if(fetchedPaymentDetails.isEmpty()) return ResponseEntity.ok(new ApiWrapperClass<>("No Payment had Made till now",true,null,null));
  else {
   for(PaymentPojo singlePaymentDetails:fetchedPaymentDetails){
    PaymentResponseDto paymentResponseDto=mapperInterface.PaymentPojoToPaymentResponseDto(singlePaymentDetails);
    paymentResponseDtos.add(paymentResponseDto);
   }
   redisTemplate.opsForHash().put(generalKey+userID,userID,paymentResponseDtos);
   redisTemplate.expire(generalKey+userID, Duration.ofMinutes(10));
   return ResponseEntity.status(200).body(new ApiWrapperClass<>("Payment Recorded fetched Successfully:",true,paymentResponseDtos,null));
  }
 }
}
