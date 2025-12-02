package com.ecom.EcomApplication.Service.User.Impl;

import com.ecom.EcomApplication.Handler.UserNotFoundException;
import com.ecom.EcomApplication.Model.Address.Address;
import com.ecom.EcomApplication.Repository.User.UserRepository;
import com.ecom.EcomApplication.Model.User.User;
import com.ecom.EcomApplication.Service.User.UserService;
import com.ecom.EcomApplication.dto.User.UserRequest;
import com.ecom.EcomApplication.dto.User.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(user -> objectMapper.convertValue(user, UserResponse.class))
                .toList();
    }

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        User user = objectMapper.convertValue(userRequest, User.class);
        userRepository.save(user);
        return objectMapper.convertValue(user, UserResponse.class);
    }

    @Override
    public UserResponse getUserById(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User does not exist"));
        return objectMapper.convertValue(user, UserResponse.class);
    }

    @Override
    public UserResponse updateUserLastName(UserRequest updatedUser, Long id) throws UserNotFoundException {

        return userRepository.findById(id)
                .map(exixtingUser ->
                {
                    exixtingUser.setFirstName(updatedUser.getFirstName());
                    exixtingUser.setLastName(updatedUser.getLastName());
                    exixtingUser.setEmail(updatedUser.getEmail());
                    exixtingUser.setPhoneNumber(updatedUser.getPhoneNumber());
                    if(updatedUser.getAddress() != null) {
                        Address address = objectMapper.convertValue(updatedUser.getAddress(), Address.class);
                        exixtingUser.setAddress(address);
                    }
                    userRepository.save(exixtingUser);
                    return objectMapper.convertValue(exixtingUser, UserResponse.class);
                })
                .orElseThrow(() -> new UserNotFoundException("User does not exist"));
    }
}
