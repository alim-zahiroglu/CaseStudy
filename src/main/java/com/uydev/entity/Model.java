package com.uydev.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Model  extends BaseEntity {

    private String name;

    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    private Integer fixedPercentage;
    private Integer monthlyPercentage;
    private Integer weeklyPercentage;
}
