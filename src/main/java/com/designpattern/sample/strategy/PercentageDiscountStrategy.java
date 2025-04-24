package com.designpattern.sample.strategy;

import org.springframework.stereotype.Component;

/**
 * 퍼센트 할인 전략 구현체
 */
@Component
public class PercentageDiscountStrategy implements DiscountStrategy {
    private final double percentage;
    
    public PercentageDiscountStrategy() {
        // 기본 10% 할인
        this.percentage = 10.0;
    }
    
    public PercentageDiscountStrategy(double percentage) {
        this.percentage = percentage;
    }
    
    @Override
    public double applyDiscount(double price) {
        return price - (price * percentage / 100);
    }
    
    @Override
    public String getStrategyName() {
        return "퍼센트 할인 (" + percentage + "%)";
    }
}
