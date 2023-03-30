package com.ivanov.laboratory.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDTO {

    private Long id;

    private List<Integer> analyzeIds;

    private LocalDate dateOfOrder;
}
