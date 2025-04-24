package com.designpattern.sample.strategy;

import org.springframework.stereotype.Component;

/**
 * 할인 없음 전략 구현체
 */
@Component
public class NoDiscountStrategy implements DiscountStrategy {
    
    @Override
    public double applyDiscount(double price) {
        return price; // 할인 없음
    }
    
    @Override
    public String getStrategyName() {
        return "할인 없음";
    }
}
