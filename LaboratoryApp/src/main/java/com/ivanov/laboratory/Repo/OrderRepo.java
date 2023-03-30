package com.ivanov.laboratory.Repo;

import com.ivanov.laboratory.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {

    Order findByClinicOrderId(long id);
}
