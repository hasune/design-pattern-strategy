package com.designpattern.sample.simplestrategy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SimpleStrategyTest {

    @Autowired
    private SimplePaymentService paymentService;
    
    @Test
    public void testCreditCardPayment() {
        String result = paymentService.processPayment("card", 1000);
        assertEquals("신용카드로 1000.0원을 결제했습니다.", result);
    }
    
    @Test
    public void testKakaoPayPayment() {
        String result = paymentService.processPayment("kakao", 2000);
        assertEquals("카카오페이로 2000.0원을 결제했습니다.", result);
    }
    
    @Test
    public void testBankTransferPayment() {
        String result = paymentService.processPayment("bank", 3000);
        assertEquals("계좌이체로 3000.0원을 결제했습니다.", result);
    }
    
    @Test
    public void testInvalidPaymentMethod() {
        String result = paymentService.processPayment("invalid", 1000);
        assertEquals("지원하지 않는 결제 방법입니다.", result);
    }
}
