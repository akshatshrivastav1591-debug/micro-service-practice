package com.MicoservicePractice.UserService.Service;

import com.MicoservicePractice.UserService.ApiWrapperClass.ApiWrapperClass;
import com.MicoservicePractice.UserService.Dto.UpdateUserDataRequestDto;
import com.MicoservicePractice.UserService.Dto.UserRequestDto;

import com.MicoservicePractice.UserService.Dto.UserResponseDto;
import com.MicoservicePractice.UserService.ExceptionHandeller.CustomExceptions.ConflictResourceException;
import com.MicoservicePractice.UserService.ExceptionHandeller.CustomExceptions.MissingFieldException;
import com.MicoservicePractice.UserService.ExceptionHandeller.CustomExceptions.ResourceNotFoundException;
import com.MicoservicePractice.UserService.Mapper.MapperInterface;
import com.MicoservicePractice.UserService.Pojo.UserPojo;
import com.MicoservicePractice.UserService.Repository.UserRepo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;


@Service
public class UserService {
    @Autowired
    private  UserRepo userRepo;
    @Autowired
   private  MapperInterface mapper;
    @Autowired
    private  RedisTemplate<String,Object> redisTemplate;
    private final static String generalKey="User";
    public ResponseEntity<ApiWrapperClass<Void>> addNewUserEntry(@Valid UserRequestDto newUserDto){
        if(userRepo.existsByEmailID(newUserDto.getEmailID())) throw new ConflictResourceException("User Already Exists:");
        UserPojo newUserPojo=mapper.userRequestDtoToUserPojo(newUserDto);
        userRepo.save(newUserPojo);
        return ResponseEntity.status(201).body(new ApiWrapperClass<>("User Registered Successfully:",true,null,null));
    }
    public ResponseEntity<ApiWrapperClass<UserResponseDto>> getUserEntry(String  userID) {

       UserPojo cachedUserPojo=(UserPojo) redisTemplate.opsForHash().get(getObjectKey(userID), String.valueOf(userID));
       if(cachedUserPojo!=null) return ResponseEntity.ok(new ApiWrapperClass<>("User Fetched Successfully",true,mapper.userPojoToUserResponseDto(cachedUserPojo),null));
      else {
          UserPojo fetchedUserPojo=userRepo.findById(userID).orElseThrow(()-> new ResourceNotFoundException("User is Not Found In Database"));
          redisTemplate.opsForHash().put(getObjectKey(userID),fetchedUserPojo.getUserID(),fetchedUserPojo);
          redisTemplate.expire(getObjectKey(userID), Duration.ofMinutes(10));
           return ResponseEntity.ok(new ApiWrapperClass<>("User Fetched Successfully:",true,mapper.userPojoToUserResponseDto(fetchedUserPojo),null));
      }
    }
    public ResponseEntity<ApiWrapperClass<Void>> updateUserInfo(UpdateUserDataRequestDto updatedUserInfoDto){
        boolean hasEmail = updatedUserInfoDto.getUpdatedUserEmailId() != null
                && !updatedUserInfoDto.getUpdatedUserEmailId().isBlank();

        boolean hasPassword = updatedUserInfoDto.getUpdatedUserPassword() != null
                && !updatedUserInfoDto.getUpdatedUserPassword().isBlank();
         if(!hasEmail && !hasPassword){
             throw  new MissingFieldException("Please Enter all the Required Updating Info");
         }
         UserPojo oldUserPojo=userRepo.findById(updatedUserInfoDto.getUserID()).orElseThrow(()-> new ResourceNotFoundException("User not exists"));
        //If user wants to change only Email
         if(hasEmail){
             String newEmail= updatedUserInfoDto.getUpdatedUserEmailId();
             if(oldUserPojo.getEmailID().equals(newEmail)) throw  new ConflictResourceException("Please Enter Different Email Than Previous One:");
             if(userRepo.existsByEmailID(newEmail)) throw  new ConflictResourceException("Account with this email already exists:");
             oldUserPojo.setEmailID(newEmail);
         }

         //If user wants to change only password
         if(hasPassword){
             String updatedPassword= updatedUserInfoDto.getUpdatedUserPassword();
             if(oldUserPojo.getPassword().equals(updatedPassword)) throw  new ConflictResourceException("Please Enter Different Password Than Previous One:");
             oldUserPojo.setPassword(updatedPassword);
         }


         userRepo.save(oldUserPojo);
        redisTemplate.opsForHash().put(getObjectKey(oldUserPojo.getUserID()),oldUserPojo.getUserID(),oldUserPojo);

        return ResponseEntity.ok(new ApiWrapperClass<>("User Info Updated Successfully",true,null,null));
    }


    public ResponseEntity<ApiWrapperClass<Void>> deleteUser(@NotEmpty(message = "UserID can't be empty") String deletingUserId) {
        if(!userRepo.existsById(deletingUserId)) throw new ResourceNotFoundException("User not Found:");
        userRepo.deleteById(deletingUserId);
        redisTemplate.opsForHash().delete(getObjectKey(deletingUserId),deletingUserId);
        return ResponseEntity.ok(new ApiWrapperClass<>("User Info had deleted Successfully",true,null,null));

    }

    private String getObjectKey(String userId){
        return generalKey+userId;
    }
}
