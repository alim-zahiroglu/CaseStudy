package com.uydev.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModelDto {

    private Long id;
    private String name;
    private boolean isActive; // Only active models are included in calculations


    private Integer fixedPercentage;
    private Integer monthlyPercentage;
    private Integer weeklyPercentage;

    private Long projectId;
}
