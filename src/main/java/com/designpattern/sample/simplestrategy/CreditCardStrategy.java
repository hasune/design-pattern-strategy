package com.designpattern.sample.simplestrategy;

import org.springframework.stereotype.Component;

/**
 * 신용카드 결제 전략
 */
@Component
public class CreditCardStrategy implements PaymentStrategy {
    
    @Override
    public String pay(double amount) {
        return "신용카드로 " + amount + "원을 결제했습니다.";
    }
}
