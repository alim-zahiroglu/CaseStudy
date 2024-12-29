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
@Table(name = "models")
public class Model extends BaseEntity {

    private String name;
    private boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}
