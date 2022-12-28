package com.kefas.ActivityTracker.dto;


import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsersDto {

    @NotBlank(message = "First Name should not be Empty")
    private String firstname;

    @NotBlank(message = "Last Name should not be Empty")
    private String lastname;

    @NotBlank(message = "Email not Valid")
    private String email;

    @NotBlank(message = "Phone Number should not be Empty")
    private String phoneNumber;

    @NotBlank(message = "Password should not be Empty")
    private String password;
}
