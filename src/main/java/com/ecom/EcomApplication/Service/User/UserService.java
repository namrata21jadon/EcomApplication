package com.ecom.EcomApplication.Service.User;

import com.ecom.EcomApplication.Handler.UserNotFoundException;
import com.ecom.EcomApplication.dto.User.UserRequest;
import com.ecom.EcomApplication.dto.User.UserResponse;

import java.util.List;

public interface UserService {
    public List<UserResponse> getAllUsers();
    public UserResponse addUser(UserRequest userRequest);
    public UserResponse getUserById(Long id) throws UserNotFoundException;
    public UserResponse updateUserLastName(UserRequest updatedUser , Long id) throws UserNotFoundException;
}
