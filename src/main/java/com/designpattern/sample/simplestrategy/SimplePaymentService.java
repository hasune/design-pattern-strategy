package com.designpattern.sample.simplestrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 결제 서비스
 */
@Service
public class SimplePaymentService {
    
    private final CreditCardStrategy creditCardStrategy;
    private final KakaoPayStrategy kakaoPayStrategy;
    private final BankTransferStrategy bankTransferStrategy;
    
    // 컨텍스트 객체
    private final PaymentContext paymentContext;
    
    @Autowired
    public SimplePaymentService(
            CreditCardStrategy creditCardStrategy,
            KakaoPayStrategy kakaoPayStrategy,
            BankTransferStrategy bankTransferStrategy) {
        
        this.creditCardStrategy = creditCardStrategy;
        this.kakaoPayStrategy = kakaoPayStrategy;
        this.bankTransferStrategy = bankTransferStrategy;
        
        // 컨텍스트 객체 생성
        this.paymentContext = new PaymentContext();
    }
    
    /**
     * 결제 방법을 선택하고 결제를 진행합니다.
     * @param paymentMethod 결제 방법 ("card", "kakao", "bank")
     * @param amount 결제 금액
     * @return 결제 결과
     */
    public String processPayment(String paymentMethod, double amount) {
        // 결제 방법에 따라 전략 선택
        switch (paymentMethod.toLowerCase()) {
            case "card":
                paymentContext.setPaymentStrategy(creditCardStrategy);
                break;
            case "kakao":
                paymentContext.setPaymentStrategy(kakaoPayStrategy);
                break;
            case "bank":
                paymentContext.setPaymentStrategy(bankTransferStrategy);
                break;
            default:
                return "지원하지 않는 결제 방법입니다.";
        }
        
        // 선택된 전략으로 결제 실행
        return paymentContext.executePayment(amount);
    }
}
