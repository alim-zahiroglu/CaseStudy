package com.uydev.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mode_percentages")
public class ModePercentage extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "configuration_id", nullable = false)
    private Configuration configuration;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    private int percentage; // Percentage for this model in the specified mode
}
