package com.ecom.EcomApplication.Controller.User;

import com.ecom.EcomApplication.Handler.UserNotFoundException;
import com.ecom.EcomApplication.Service.User.UserService;
import com.ecom.EcomApplication.dto.User.UserRequest;
import com.ecom.EcomApplication.dto.User.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    
    private final UserService userService;
    
    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public UserResponse getUserById(@PathVariable Long id) throws UserNotFoundException {
        return userService.getUserById(id);
    }
    
    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest userRequest){
        return userService.addUser(userRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUserLastName(@RequestBody UserRequest updatedUser, @PathVariable Long id) throws UserNotFoundException{
        UserResponse userResponse = userService.updateUserLastName(updatedUser, id);
        return ResponseEntity.ok().body(userResponse);
    }
}
