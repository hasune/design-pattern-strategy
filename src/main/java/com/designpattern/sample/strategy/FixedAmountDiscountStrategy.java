package com.designpattern.sample.strategy;

import org.springframework.stereotype.Component;

/**
 * 고정 금액 할인 전략 구현체
 */
@Component
public class FixedAmountDiscountStrategy implements DiscountStrategy {
    private final double discountAmount;
    
    public FixedAmountDiscountStrategy() {
        // 기본 1000원 할인
        this.discountAmount = 1000.0;
    }
    
    public FixedAmountDiscountStrategy(double discountAmount) {
        this.discountAmount = discountAmount;
    }
    
    @Override
    public double applyDiscount(double price) {
        return Math.max(0, price - discountAmount);
    }
    
    @Override
    public String getStrategyName() {
        return "고정 금액 할인 (" + discountAmount + "원)";
    }
}
