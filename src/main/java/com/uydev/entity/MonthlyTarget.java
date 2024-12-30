package com.uydev.entity;

import com.uydev.enums.Month;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class MonthlyTarget extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private Month month; // e.g., "January"
    private int target;   // e.g., 1000

    @ManyToOne
    private Project project;
}
