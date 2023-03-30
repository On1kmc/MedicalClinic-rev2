package com.ivanov.MedicalClinic.mapper;

import com.ivanov.MedicalClinic.dto.AnalyzeDTO;
import com.ivanov.MedicalClinic.model.Analyze;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnalyzeMapper {

    AnalyzeDTO toDTO(Analyze analyze);

    Analyze toAnalyze(AnalyzeDTO analyzeDTO);
}
