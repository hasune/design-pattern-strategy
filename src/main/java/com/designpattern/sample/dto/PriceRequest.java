package com.designpattern.sample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 가격 계산 요청 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceRequest {
    private double price;
}
