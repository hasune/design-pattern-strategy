package com.designpattern.sample.strategy;

/**
 * 전략 패턴의 전략 인터페이스
 * 여러 할인 알고리즘을 캡슐화하기 위한 인터페이스
 */
public interface DiscountStrategy {
    /**
     * 주어진 가격에 할인을 적용합니다.
     * @param price 원래 가격
     * @return 할인 적용된 가격
     */
    double applyDiscount(double price);
    
    /**
     * 전략의 이름을 반환합니다.
     * @return 전략 이름
     */
    String getStrategyName();
}
