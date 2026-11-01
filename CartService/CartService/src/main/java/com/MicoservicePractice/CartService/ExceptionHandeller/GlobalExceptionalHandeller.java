package com.MicoservicePractice.CartService.ExceptionHandeller;

import com.MicoservicePractice.CartService.ApiWrapperClass.ApiWrapperClass;
import com.MicoservicePractice.CartService.ExceptionHandeller.CustomExceptions.ConflictResourceException;
import com.MicoservicePractice.CartService.ExceptionHandeller.CustomExceptions.MissingFieldException;
import com.MicoservicePractice.CartService.ExceptionHandeller.CustomExceptions.ResourceNotFoundException;
import com.MicoservicePractice.CartService.ExceptionHandeller.Dto.ExceptionDto;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@ControllerAdvice
@Slf4j
public class GlobalExceptionalHandeller extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiWrapperClass<Void>> genericExceptionMethod(Exception e, HttpServletRequest request){
        System.out.println("Error:"+e.getLocalizedMessage());
        return  ResponseEntity.status(500).body(new ApiWrapperClass<>("Server error",false,null,new ExceptionDto("Server Error",Map.of("Server Error","Something went wrong with server:"), request.getRequestURI(), LocalDateTime.now())));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiWrapperClass<Void>> resourceNotFoundExceptionHandlerMethod(ResourceNotFoundException e, HttpServletRequest request){
        log.error("Resource Not Found Exception:{}", e.getMessage());
        return  ResponseEntity.status(404).body(new ApiWrapperClass<>("Resource Not found:",false,null,new ExceptionDto("Resource Not found Exception",Map.of("Duplicate Resource",e.getMessage()), request.getRequestURI(), LocalDateTime.now())));
    }

    @ExceptionHandler(ConflictResourceException.class)
    public ResponseEntity<ApiWrapperClass<Void>> handleConflictResourceException(ConflictResourceException e, HttpServletRequest request){
        log.error("Conflict Resource Exception:{}", e.getMessage());
        return  ResponseEntity.status(409).body(new ApiWrapperClass<>("Resource Conflict:",false,null,new ExceptionDto("Resource Conflict Exception",Map.of("Conflict resource",e.getMessage()), request.getRequestURI(), LocalDateTime.now())));
    }

    @ExceptionHandler(MissingFieldException.class)
    public ResponseEntity<ApiWrapperClass<Void>> handleMissingFieldException(MissingFieldException e, HttpServletRequest request){
        log.error("Missing Field Exception:{}", e.getMessage());
        return  ResponseEntity.status(400).body(new ApiWrapperClass<>("Something Missing in field:",false,null,new ExceptionDto("MissingFieldException",Map.of("MissingField Exception",e.getMessage()), request.getRequestURI(), LocalDateTime.now())));
    }

    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @Nullable HttpHeaders headers, @Nullable HttpStatusCode status, @Nullable WebRequest request) {
        Map<String,String> validationErrors=new HashMap<>();
        List<ObjectError> validationErrorsList=ex.getBindingResult().getAllErrors();
        validationErrorsList.forEach((error)->{
            String fieldName=((FieldError)error).getField();
            String validationMessages=error.getDefaultMessage();
            validationErrors.put(fieldName,validationMessages);
        });
        String path = ((ServletWebRequest) request).getRequest().getRequestURI();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiWrapperClass<>("Validation Exception",false,null,new ExceptionDto("Validation Error",validationErrors,path,LocalDateTime.now())));
    }

}
