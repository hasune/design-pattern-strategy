package com.designpattern.sample.controller;

import com.designpattern.sample.dto.PriceRequest;
import com.designpattern.sample.dto.StrategyRequest;
import com.designpattern.sample.model.PriceResult;
import com.designpattern.sample.service.PriceService;
import com.designpattern.sample.strategy.DiscountStrategy;
import com.designpattern.sample.strategy.DiscountStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 할인 전략을 적용하고 관리하는 API 컨트롤러
 */
@RestController
@RequestMapping("/api/discount")
public class DiscountController {

    private final PriceService priceService;
    private final DiscountStrategyFactory strategyFactory;

    @Autowired
    public DiscountController(PriceService priceService, DiscountStrategyFactory strategyFactory) {
        this.priceService = priceService;
        this.strategyFactory = strategyFactory;
    }

    /**
     * 현재 적용된 할인 전략 정보를 조회합니다.
     */
    @GetMapping("/strategy")
    public ResponseEntity<Map<String, String>> getCurrentStrategy() {
        Map<String, String> response = new HashMap<>();
        response.put("currentStrategy", priceService.getCurrentStrategyName());
        return ResponseEntity.ok(response);
    }

    /**
     * 할인 전략을 변경합니다.
     */
    @PostMapping("/strategy")
    public ResponseEntity<Map<String, String>> changeStrategy(@RequestBody StrategyRequest request) {
        String strategyName = priceService.changeStrategy(request.getStrategyName());
        
        Map<String, String> response = new HashMap<>();
        response.put("strategyChanged", strategyName);
        return ResponseEntity.ok(response);
    }

    /**
     * 모든 사용 가능한 할인 전략 목록을 조회합니다.
     */
    @GetMapping("/strategies")
    public ResponseEntity<Map<String, String>> getAllStrategies() {
        Map<String, DiscountStrategy> allStrategies = strategyFactory.getAllStrategies();
        Map<String, String> response = new HashMap<>();
        
        for (Map.Entry<String, DiscountStrategy> entry : allStrategies.entrySet()) {
            response.put(entry.getKey(), entry.getValue().getStrategyName());
        }
        
        return ResponseEntity.ok(response);
    }

    /**
     * 주어진 가격에 현재 할인 전략을 적용하여 결과를 반환합니다.
     */
    @PostMapping("/calculate")
    public ResponseEntity<PriceResult> calculatePrice(@RequestBody PriceRequest request) {
        double originalPrice = request.getPrice();
        double discountedPrice = priceService.calculatePrice(originalPrice);
        
        PriceResult result = new PriceResult(
            originalPrice,
            discountedPrice,
            priceService.getCurrentStrategyName()
        );
        
        return ResponseEntity.ok(result);
    }
}
