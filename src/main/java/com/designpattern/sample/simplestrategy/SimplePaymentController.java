package com.designpattern.sample.simplestrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 간단한 결제 컨트롤러
 */
@RestController
@RequestMapping("/api/simple-payment")
public class SimplePaymentController {
    
    private final SimplePaymentService paymentService;
    
    @Autowired
    public SimplePaymentController(SimplePaymentService paymentService) {
        this.paymentService = paymentService;
    }
    
    /**
     * 결제를 처리합니다.
     * @param method 결제 방법
     * @param amount 결제 금액
     * @return 결제 결과
     */
    @PostMapping
    public ResponseEntity<Map<String, String>> makePayment(
            @RequestParam String method,
            @RequestParam double amount) {
        
        String result = paymentService.processPayment(method, amount);
        
        Map<String, String> response = new HashMap<>();
        response.put("result", result);
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * 사용 가능한 결제 방법을 조회합니다.
     */
    @GetMapping("/methods")
    public ResponseEntity<Map<String, String>> getPaymentMethods() {
        Map<String, String> methods = new HashMap<>();
        methods.put("card", "신용카드 결제");
        methods.put("kakao", "카카오페이 결제");
        methods.put("bank", "계좌이체 결제");
        
        return ResponseEntity.ok(methods);
    }
}
