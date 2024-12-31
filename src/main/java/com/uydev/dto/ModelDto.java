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

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int modelTotal;

    @NumberFormat
    @Min(value = 0, message = "CurrentPercentage should be between 0 ~ 100")
    private Integer currentPercentage = 0;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long projectId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String projectName;

    //    private Integer fixedPercentage;
//    private Integer monthlyPercentage;
//    private Integer weeklyPercentage;
}
