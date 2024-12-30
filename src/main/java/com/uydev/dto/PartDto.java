package com.uydev.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartDto {

    private Long id;
    private String name;
    private int quantity; // Quantity of this part in a model

    private Long modelId; // Reference to the model (avoiding full Model object)
}
