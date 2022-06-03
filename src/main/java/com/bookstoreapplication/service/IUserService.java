package com.bookstoreapplication.service;

import com.bookstoreapplication.dto.ResponseDTO;
import com.bookstoreapplication.dto.UserDTO;
import com.bookstoreapplication.dto.UserLoginDTO;
import com.bookstoreapplication.model.UserRegistration;

import java.util.List;

/**
 * Created IUserService interface to achieve abstraction
 */
public interface IUserService {

    String addUser(UserDTO userDTO);

    List<UserRegistration> getAllUsers();

    ResponseDTO loginUser(UserLoginDTO userLoginDTO);

    Object getUserById(String token);

    String forgotPassword(String email, String password);

    String getToken(String email);

    List<UserRegistration> getAllUserDataByToken(String token);

    UserRegistration updateRecordByToken(Integer id, UserDTO userDTO);

}