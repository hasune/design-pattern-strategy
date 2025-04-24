package com.designpattern.sample.strategy;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 할인 전략을 생성하고 관리하는 팩토리 클래스
 */
@Component
public class DiscountStrategyFactory {
    private final Map<String, DiscountStrategy> strategies = new HashMap<>();
    
    public DiscountStrategyFactory() {
        // 기본 전략들 등록
        strategies.put("percentage", new PercentageDiscountStrategy());
        strategies.put("fixed", new FixedAmountDiscountStrategy());
        strategies.put("none", new NoDiscountStrategy());
        
        // 추가 전략 예시
        strategies.put("percentage15", new PercentageDiscountStrategy(15.0));
        strategies.put("fixed2000", new FixedAmountDiscountStrategy(2000.0));
    }
    
    /**
     * 지정된 이름의 할인 전략을 반환합니다.
     * @param strategyName 전략 이름
     * @return 할인 전략 구현체
     */
    public DiscountStrategy getStrategy(String strategyName) {
        DiscountStrategy strategy = strategies.get(strategyName);
        if (strategy == null) {
            // 기본 전략으로 "none" 반환
            return strategies.get("none");
        }
        return strategy;
    }
    
    /**
     * 사용 가능한 모든 전략을 반환합니다.
     * @return 전략 맵
     */
    public Map<String, DiscountStrategy> getAllStrategies() {
        return new HashMap<>(strategies);
    }
}
