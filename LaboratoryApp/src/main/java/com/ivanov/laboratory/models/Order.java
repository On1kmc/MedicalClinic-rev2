package com.ivanov.laboratory.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "Orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    private Long clinicOrderId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_analyze",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "analyze_id"))
    private List<Analyze> analyzeList;


    @OneToMany(mappedBy = "order")
    private List<Task> taskList;

    private LocalDate dateOfOrder;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", analyzeList=" + analyzeList +
                '}';
    }
}


