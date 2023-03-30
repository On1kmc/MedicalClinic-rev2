package com.ivanov.MedicalClinic.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDTO {

    private Long id;

    private List<AnalyzeDTO> analyzeList;

    private LocalDate dateOfOrder;
}
