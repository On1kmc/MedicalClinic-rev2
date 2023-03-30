package com.ivanov.MedicalClinic.Repo;

import com.ivanov.MedicalClinic.model.Analyze;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnalyzeRepo extends JpaRepository<Analyze, Integer> {

}
