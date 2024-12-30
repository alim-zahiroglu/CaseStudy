package com.uydev.dto;

import com.uydev.enums.ConfigType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjectDto {
    private Long id;
    private String name;
    private String configType;

    // List of monthly targets
    private List<MonthlyTargetDTO> monthlyTargets;



}
