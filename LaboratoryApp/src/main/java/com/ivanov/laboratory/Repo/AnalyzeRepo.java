package com.ivanov.laboratory.Repo;

import com.ivanov.laboratory.models.Analyze;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyzeRepo extends JpaRepository<Analyze, Integer> {
}
