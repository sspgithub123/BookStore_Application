package com.bookstoreapplication.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * Data transfer object to log in user
 */
@Data
public class UserLoginDTO {
    @Email
    private String email;
    @NotEmpty(message = "Password cant be null")
    private String password;
    public UserLoginDTO(String email,String password){
        this.email=email;
        this.password=password;
    }

}
