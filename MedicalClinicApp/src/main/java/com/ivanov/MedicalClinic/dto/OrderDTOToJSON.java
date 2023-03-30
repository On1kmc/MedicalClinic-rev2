package com.ivanov.MedicalClinic.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTOToJSON {
    private Long id;

    private List<Integer> analyzeIds;
}
