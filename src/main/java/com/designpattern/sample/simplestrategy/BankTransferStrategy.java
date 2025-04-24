package com.designpattern.sample.simplestrategy;

import org.springframework.stereotype.Component;

/**
 * 계좌이체 결제 전략
 */
@Component
public class BankTransferStrategy implements PaymentStrategy {
    
    @Override
    public String pay(double amount) {
        return "계좌이체로 " + amount + "원을 결제했습니다.";
    }
}
