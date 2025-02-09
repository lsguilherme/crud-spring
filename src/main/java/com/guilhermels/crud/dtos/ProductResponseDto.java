package com.guilhermels.crud.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponseDto(Integer id, String name, BigDecimal price, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
