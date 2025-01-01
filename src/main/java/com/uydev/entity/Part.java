package com.uydev.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parts")
public class Part extends BaseEntity{

    private String name;
    private int quantityPerModel; // Quantity of this part in a model

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;
}
