package com.designpattern.sample.simplestrategy;

/**
 * 결제를 처리하는 컨텍스트 클래스
 */
public class PaymentContext {
    // 현재 사용할 결제 전략
    private PaymentStrategy paymentStrategy;
    
    // 생성자
    public PaymentContext() {
        // 기본 전략 없음
    }
    
    // 전략을 설정하는 메서드
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
    
    // 결제를 실행하는 메서드
    public String executePayment(double amount) {
        if (paymentStrategy == null) {
            return "결제 방법이 선택되지 않았습니다.";
        }
        return paymentStrategy.pay(amount);
    }
}
