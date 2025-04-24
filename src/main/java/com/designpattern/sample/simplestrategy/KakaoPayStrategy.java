package com.designpattern.sample.simplestrategy;

import org.springframework.stereotype.Component;

/**
 * 카카오페이 결제 전략
 */
@Component
public class KakaoPayStrategy implements PaymentStrategy {
    
    @Override
    public String pay(double amount) {
        return "카카오페이로 " + amount + "원을 결제했습니다.";
    }
}
