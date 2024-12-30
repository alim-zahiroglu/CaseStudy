package com.uydev.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Model  extends BaseEntity {

    private String name;

    private boolean isActive = true; // Only active models are included in calculations

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    private Integer fixedPercentage;
    private Integer monthlyPercentage;
    private Integer weeklyPercentage;
}
