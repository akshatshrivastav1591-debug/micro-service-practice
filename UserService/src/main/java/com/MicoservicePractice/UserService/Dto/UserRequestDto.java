package com.MicoservicePractice.UserService.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequestDto {
    @NotEmpty(message = "Email Can't be Empty")
    @Email(message = "Please Enter Valid EmailId:")
    private String emailID;
    @NotEmpty(message = "Password can't be Empty")
    @Size(min = 6,max = 30,message = "Please enter password in between 6 and 30 Characters")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$",
            message = "Password must contain a digit, a lowercase letter, an uppercase letter, and a special character (@#$%^&+=!)"
    )
    private String password;
}
