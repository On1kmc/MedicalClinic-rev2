package com.ivanov.laboratory.services;

import com.ivanov.laboratory.Repo.AnalyzeRepo;
import com.ivanov.laboratory.models.Analyze;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalyzeService {

    private final AnalyzeRepo analyzeRepo;

    @Autowired
    public AnalyzeService(AnalyzeRepo analyzeRepo) {
        this.analyzeRepo = analyzeRepo;
    }


    public List<Analyze> getAnalyzesById(List<Integer> ids) {
        Iterable<Integer> iterable = new ArrayList<>(ids);
        return analyzeRepo.findAllById(iterable);
    }

    public List<Analyze> findAll() {
        return analyzeRepo.findAll();
    }

}
