package com.ivanov.MedicalClinic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "Analyzes")
public class Analyze {

    String name;

    String description;

    @Id
    private Integer id;

    @Transient
    private String status;


}
