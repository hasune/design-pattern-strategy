package com.designpattern.sample.simplestrategy;

/**
 * 간단한 전략 패턴의 인터페이스
 */
public interface PaymentStrategy {
    /**
     * 결제를 처리합니다.
     * @param amount 결제할 금액
     * @return 결제 과정에 대한 설명
     */
    String pay(double amount);
}
