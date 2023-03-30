package com.ivanov.MedicalClinic.Repo;

import com.ivanov.MedicalClinic.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {

}
