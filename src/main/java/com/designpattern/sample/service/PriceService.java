package com.designpattern.sample.service;

import com.designpattern.sample.strategy.DiscountStrategy;
import com.designpattern.sample.strategy.DiscountStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 전략 패턴의 컨텍스트 역할을 하는 서비스 클래스
 * 실제로 전략을 사용하여 가격을 계산합니다.
 */
@Service
public class PriceService {
    
    private final DiscountStrategyFactory strategyFactory;
    private DiscountStrategy currentStrategy;
    
    @Autowired
    public PriceService(DiscountStrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
        // 기본 전략 설정
        this.currentStrategy = strategyFactory.getStrategy("none");
    }
    
    /**
     * 현재 적용된 할인 전략으로 가격을 계산합니다.
     * @param originalPrice 원래 가격
     * @return 할인이 적용된 가격
     */
    public double calculatePrice(double originalPrice) {
        return currentStrategy.applyDiscount(originalPrice);
    }
    
    /**
     * 할인 전략을 변경합니다.
     * @param strategyName 적용할 전략 이름
     * @return 변경된 전략 이름
     */
    public String changeStrategy(String strategyName) {
        currentStrategy = strategyFactory.getStrategy(strategyName);
        return currentStrategy.getStrategyName();
    }
    
    /**
     * 현재 적용된 전략 이름을 반환합니다.
     * @return 현재 적용된 전략 이름
     */
    public String getCurrentStrategyName() {
        return currentStrategy.getStrategyName();
    }
}
