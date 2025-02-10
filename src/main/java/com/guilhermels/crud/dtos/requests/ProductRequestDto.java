package com.guilhermels.crud.dtos.requests;

import java.math.BigDecimal;

public record ProductRequestDto(String name, BigDecimal price, String description) {
}
