package com.ecom.EcomApplication.dto.User;

import com.ecom.EcomApplication.Model.User.UserRole;
import com.ecom.EcomApplication.dto.Address.AddressDTO;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long phoneNumber;
    private UserRole role;
    private AddressDTO address;
}
