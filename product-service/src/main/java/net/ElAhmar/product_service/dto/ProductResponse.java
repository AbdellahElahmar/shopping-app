package net.ElAhmar.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public record ProductResponse(String id, String name, String description, BigDecimal price) {

}
