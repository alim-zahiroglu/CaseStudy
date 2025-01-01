package com.uydev.dto;

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
public class PartDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Name should not be blank")
    @Size(min = 2, max = 50, message = "Name must be 2~50 character long")
    private String name;

    @NumberFormat
    @Min(value = 1, message = "quantityPerModel should be grater then or equal to 1")
    private int quantityPerModel;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int quantityInTotal; // Quantity of this part in all models

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long modelId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String modelName; // Reference to the model (avoiding full Model object)
}
