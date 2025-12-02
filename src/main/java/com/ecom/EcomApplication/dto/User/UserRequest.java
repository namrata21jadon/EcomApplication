package com.ecom.EcomApplication.dto.User;

import com.ecom.EcomApplication.dto.Address.AddressDTO;
import lombok.Data;

@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private Long phoneNumber;
    private AddressDTO address;
}
