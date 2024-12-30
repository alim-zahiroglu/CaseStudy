package com.uydev.dto;

import com.uydev.enums.Month;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyTargetDTO {

    private Long id;
    private String month = LocalDate.now().getMonth().toString();
    private int target = 1000;

    private Long projectId;
}
