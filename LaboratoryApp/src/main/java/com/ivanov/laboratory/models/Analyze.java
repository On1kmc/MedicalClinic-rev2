package com.ivanov.laboratory.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "analyzes")
public class Analyze {

    String name;
    String description;

    @Id
    public int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Analyze analyze = (Analyze) o;
        return id == analyze.id && Objects.equals(name, analyze.name) && Objects.equals(description, analyze.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, id);
    }
}

