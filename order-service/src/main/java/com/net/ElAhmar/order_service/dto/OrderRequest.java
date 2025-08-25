package com.net.ElAhmar.order_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(Long id, String skuCode, BigDecimal price, Integer quantity) {


}
