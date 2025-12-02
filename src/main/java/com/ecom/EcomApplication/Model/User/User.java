package com.ecom.EcomApplication.Model.User;

import com.ecom.EcomApplication.Model.Address.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity(name = "user_table")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long phoneNumber;
    private UserRole role = UserRole.CUSTOMER;

    @OneToOne(cascade = CascadeType.ALL , orphanRemoval = true)
    @JoinColumn(name = "address_id" , referencedColumnName = "id")
    private Address address;

    @CreationTimestamp
    private LocalDateTime createdTimeDate;
    @UpdateTimestamp
    private LocalDateTime updatedTimeDate;
}
