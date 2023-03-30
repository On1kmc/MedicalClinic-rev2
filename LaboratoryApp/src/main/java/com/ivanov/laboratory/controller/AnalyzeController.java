package com.ivanov.laboratory.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ivanov.laboratory.models.Analyze;
import com.ivanov.laboratory.services.AnalyzeService;
import com.ivanov.laboratory.services.ResponseService;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/get-analyze")
public class AnalyzeController {

    private final AnalyzeService analyzeService;

    private final ResponseService responseService;

    public AnalyzeController(AnalyzeService analyzeService, ResponseService responseService) {
        this.analyzeService = analyzeService;
        this.responseService = responseService;
    }

    @GetMapping
    public HttpEntity<Object> getAnalyzeDB() throws JsonProcessingException {
        List<Analyze> analyzeList = analyzeService.findAll();
        return responseService.getResponse(analyzeList);
    }

}
