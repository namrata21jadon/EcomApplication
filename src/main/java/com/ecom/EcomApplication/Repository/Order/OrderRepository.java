package com.ecom.EcomApplication.Repository.Order;

import com.ecom.EcomApplication.Model.Order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
