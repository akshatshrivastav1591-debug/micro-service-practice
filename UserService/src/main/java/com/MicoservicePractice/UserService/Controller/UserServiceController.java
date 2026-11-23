package com.MicoservicePractice.UserService.Controller;

import com.MicoservicePractice.UserService.ApiWrapperClass.ApiWrapperClass;
import com.MicoservicePractice.UserService.Dto.UpdateUserDataRequestDto;
import com.MicoservicePractice.UserService.Dto.UserRequestDto;

import com.MicoservicePractice.UserService.Dto.UserResponseDto;
import com.MicoservicePractice.UserService.Service.UserService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.TimeoutException;

@RestController
@RefreshScope
@Validated
@RequestMapping("/User")
public class UserServiceController {
    @Autowired
    UserService userService;
    @Value("${testingvalue.value}")
    private  String value;

    @PostMapping("/createNewUser")
    ResponseEntity<ApiWrapperClass<Void>> createNewUserEntry(@Valid @RequestBody UserRequestDto newUserDto) {
        return userService.addNewUserEntry(newUserDto);
    }

    @GetMapping("/getUser/{userID}")
    ResponseEntity<ApiWrapperClass<UserResponseDto>> getUser(@NotEmpty(message = "User id Can't be Empty") @PathVariable String userID) {
        System.out.println("Received UserId" + userID);
        return userService.getUserEntry(userID);
    }

    @PutMapping("/updateUser")
    ResponseEntity<ApiWrapperClass<Void>> updateUser(@Valid @RequestBody UpdateUserDataRequestDto updatedUserData) {

        return userService.updateUserInfo(updatedUserData);
    }

    @DeleteMapping("/deleteUser/{deletingUserId}")
    ResponseEntity<ApiWrapperClass<Void>> deleteUser(@NotEmpty(message = "UserID can't be empty") @PathVariable String deletingUserId) {

        return userService.deleteUser(deletingUserId);
    }

    @GetMapping("/Testing")
    public ResponseEntity<ApiWrapperClass<Void>> testingUser() {

        return ResponseEntity.ok(new ApiWrapperClass<>("User Service is working fine:", true, null, null));
    }
    @RateLimiter(name = "getValue",fallbackMethod = "limiterFallbackMethod")
//    @Retry(name = "getValue",fallbackMethod = "retryFallbackMethod")
    @GetMapping("/getValue")
    public ResponseEntity<ApiWrapperClass<String>> getValue() {
        return ResponseEntity.ok(new ApiWrapperClass<>("User Service is working fine:", true, value, null));
    }

//    public ResponseEntity<ApiWrapperClass<String>> retryFallbackMethod(Throwable throwable){
//        System.out.println("getValueFallBack method called:");
//        return ResponseEntity.status(503).body(new ApiWrapperClass<>("FallBack Method Called",false,"Retry pattern test:",null));
//    }
    public ResponseEntity<ApiWrapperClass<String>> limiterFallbackMethod(Throwable throwable){
        System.out.println("getValueFallBack method called:");
        return ResponseEntity.status(429).body(new ApiWrapperClass<>("FallBack Method Called",false,"Rate-Limiting pattern test:",null));
    }

    @GetMapping("/getAllValue/{userID}/{cartID}/{productID}")
    public ResponseEntity<ApiWrapperClass<Map<String,Object>>> getAllValue(@NotEmpty(message = "UserID can't be empty") @PathVariable String userID,
                                                                           @NotEmpty(message = "productID can't be empty") @PathVariable String productID,
                                                                           @NotEmpty(message = "cartID can't be empty") @PathVariable String cartID) {
        return userService.getAllValue(userID,productID,cartID);
    }


}
