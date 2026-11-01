package com.MicoservicePractice.PaymentService.Mapper;
import com.MicoservicePractice.PaymentService.Dto.PaymentRequestDto;
import com.MicoservicePractice.PaymentService.Dto.PaymentResponseDto;
import com.MicoservicePractice.PaymentService.Pojo.PaymentPojo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface MapperInterface {
    @Mapping(target = "paymentID",ignore = true)
    @Mapping(target = "status",ignore = true)
    @Mapping(target = "paymentTimeStamp",ignore = true)
    PaymentPojo paymentRequestDtoToPaymentPojo(PaymentRequestDto requestDto);
    PaymentResponseDto PaymentPojoToPaymentResponseDto(PaymentPojo paymentPojo);
}
