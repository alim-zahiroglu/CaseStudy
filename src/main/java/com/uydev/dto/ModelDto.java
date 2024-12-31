package com.uydev.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModelDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Name should not be blank")
    @Size(min = 2, max = 50, message = "Name must be 2~50 character long")
    private String name;


    private boolean isActive = true;

    private Integer currentPercentage;
    private Integer fixedPercentage;
    private Integer monthlyPercentage;
    private Integer weeklyPercentage;

    @NumberFormat
    @Min(value = 1, message = "Project Id should be positive integer number")
    private Long projectId;

    private String projectName;
}
