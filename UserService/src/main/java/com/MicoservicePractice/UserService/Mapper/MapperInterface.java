package com.MicoservicePractice.UserService.Mapper;

import com.MicoservicePractice.UserService.Dto.UserRequestDto;
import com.MicoservicePractice.UserService.Dto.UserResponseDto;
import com.MicoservicePractice.UserService.Pojo.UserPojo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapperInterface {
    @Mapping(target = "userID",ignore = true)
    @Mapping(target = "accountCreatedDate",ignore = true)
    UserPojo userRequestDtoToUserPojo(UserRequestDto requestDto);


    UserResponseDto userPojoToUserResponseDto(UserPojo fetchedDetails);
}
