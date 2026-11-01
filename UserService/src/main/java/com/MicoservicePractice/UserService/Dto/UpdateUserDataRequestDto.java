package com.MicoservicePractice.UserService.Dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
public class UpdateUserDataRequestDto {
    @NotEmpty(message = "UserId Can't Be Empty")
    private String userID;
    @Email(message = "Please Enter a Valid email")
    private String updatedUserEmailId;
    @Pattern(
            regexp ="^$|^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{6,30}$",
            message = "Password must contain a digit, a lowercase letter, an uppercase letter, and a special character (@#$%^&+=!) in between 6 and 30 characters:"
    )
    private String updatedUserPassword;

}
