package com.ivanov.MedicalClinic.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivanov.MedicalClinic.Repo.AnalyzeRepo;
import com.ivanov.MedicalClinic.dto.AnalyzeDTO;
import com.ivanov.MedicalClinic.mapper.AnalyzeListMapper;
import com.ivanov.MedicalClinic.model.Analyze;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalyzeService {

    private final AnalyzeRepo analyzeRepo;

    private final AnalyzeListMapper analyzeListMapper;



    @Autowired
    public AnalyzeService(AnalyzeRepo analyzeRepo, AnalyzeListMapper analyzeListMapper) {
        this.analyzeRepo = analyzeRepo;
        this.analyzeListMapper = analyzeListMapper;
    }

    public List<Analyze> getAnalyzesById(Integer... id) {
        Iterable<Integer> iterable = new ArrayList<>(List.of(id));
        return analyzeRepo.findAllById(iterable);

    }

    public List<Analyze> findAll() {
        return analyzeRepo.findAll();
    }

    public List<AnalyzeDTO> toDTOList(List<Analyze> analyzeList) {
        return analyzeListMapper.toDTOList(analyzeList);
    }

    public List<Analyze> toAnalyzeList(List<AnalyzeDTO> analyzeDTOList) {
        return analyzeListMapper.toAnalyzeList(analyzeDTOList);
    }

    @Scheduled(cron = "@daily")
    @Async
    @Transactional
    public void refreshAnalyzeDB() throws JsonProcessingException {
        String url = "http://localhost:8082/get-analyze";
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url, String.class);
        ArrayList<AnalyzeDTO> list = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(json);
        for (JsonNode s : node) {
            AnalyzeDTO analyzeDTO = mapper.readValue(s.toString(), AnalyzeDTO.class);
            list.add(analyzeDTO);
        }
        analyzeRepo.saveAll(toAnalyzeList(list));
    }
}
