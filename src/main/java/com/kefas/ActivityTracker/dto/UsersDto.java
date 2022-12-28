package com.kefas.ActivityTracker.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UsersDto {

    @NotNull(message = "First Name should not be Empty")
    private String firstname;

    @NotNull(message = "Last Name should not be Empty")
    private String lastname;

    @NotNull(message = "Email not Valid")
    private String email;

    @NotNull(message = "Phone Number should not be Empty")
    private String phoneNumber;

    @NotNull(message = "Password should not be Empty")
    private String password;
}
