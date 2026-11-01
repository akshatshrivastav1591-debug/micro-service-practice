package com.MicoservicePractice.CartService.ApiWrapperClass;


import com.MicoservicePractice.CartService.ExceptionHandeller.Dto.ExceptionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiWrapperClass<T> {
    private String message;
    private  boolean isSuccess;
    private  T data;
    private ExceptionDto errors;
}
