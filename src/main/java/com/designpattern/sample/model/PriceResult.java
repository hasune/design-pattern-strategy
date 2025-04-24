package com.designpattern.sample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 가격 계산 결과 모델 클래스
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceResult {
    private double originalPrice;
    private double discountedPrice;
    private String appliedStrategy;
}
