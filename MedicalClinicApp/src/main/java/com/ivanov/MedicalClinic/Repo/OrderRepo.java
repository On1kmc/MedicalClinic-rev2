package com.ivanov.MedicalClinic.Repo;

import com.ivanov.MedicalClinic.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    List<Order> findAllByClientId(int client_id);
}
