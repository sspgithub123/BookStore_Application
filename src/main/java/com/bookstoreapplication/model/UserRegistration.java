package com.bookstoreapplication.model;

import com.bookstoreapplication.dto.UserDTO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The @Entity annotation specifies that the class is an entity and is mapped to a database table
 */
@Entity
@Data
public class UserRegistration {
    @Id
    @GeneratedValue
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String password;

    public UserRegistration(UserDTO userDTO){
        this.userId=getUserId();
        this.firstName= userDTO.getFirstName();
        this.lastName= userDTO.getLastName();
        this.address= userDTO.getAddress();
        this.email= userDTO.getEmail();
        this.password= userDTO.getPassword();
    }
    public UserRegistration(Integer userId,UserDTO userDTO){
        this.userId=userId;
        this.firstName= userDTO.getFirstName();
        this.lastName= userDTO.getLastName();
        this.address= userDTO.getAddress();
        this.email= userDTO.getEmail();
        this.password= userDTO.getPassword();
    }

    public UserRegistration() {
    }
}