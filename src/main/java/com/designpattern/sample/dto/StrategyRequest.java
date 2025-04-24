package com.designpattern.sample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 전략 변경 요청 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StrategyRequest {
    private String strategyName;
}
