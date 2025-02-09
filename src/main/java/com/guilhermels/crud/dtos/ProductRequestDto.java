package com.guilhermels.crud.dtos;

import java.math.BigDecimal;

public record ProductRequestDto(String name, BigDecimal price, String description) {
}
