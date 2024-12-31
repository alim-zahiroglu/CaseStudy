package com.uydev.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uydev.enums.ConfigType;
import com.uydev.validation.ValidEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Name should not be blank")
    @Size(min = 2, max = 50, message = "userName must be 2~50 character long")
    private String name;

    @NotNull(message = "ConfigType shouldn't be null")
    @ValidEnum(enumClass = ConfigType.class, message = "Invalid configType,configType should one of 'Fixed','Monthly' or 'Weekly'")
    private String configType;

    @NumberFormat
    @Min(value = 1, message = "Target must be greater than or equal to 1")
    private int target;

//    private List<MonthlyTargetDto> monthlyTargets;



}
