package com.guilhermels.crud.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequestDto(
        @NotBlank(message = "Nome não pode ser vazio.")
        String name,

        @Positive(message = "Preço não pode ser negativo.")
        BigDecimal price,

        @NotBlank(message = "Nome não pode ser vazio.")
        String description
) {
}
