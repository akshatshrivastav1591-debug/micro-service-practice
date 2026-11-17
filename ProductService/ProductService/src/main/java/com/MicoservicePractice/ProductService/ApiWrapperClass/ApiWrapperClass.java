package com.MicoservicePractice.ProductService.ApiWrapperClass;


import com.MicoservicePractice.ProductService.ExceptionHandeller.Dto.ExceptionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiWrapperClass<T> {
    private String message;
    private  boolean success;
    private  T data;
    private ExceptionDto errors;
}
