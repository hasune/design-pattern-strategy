package com.designpattern.sample.strategy;

import com.designpattern.sample.service.PriceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StrategyPatternTest {

    @Autowired
    private PriceService priceService;
    
    @Test
    public void testNoDiscountStrategy() {
        priceService.changeStrategy("none");
        assertEquals(1000.0, priceService.calculatePrice(1000.0));
    }
    
    @Test
    public void testPercentageDiscountStrategy() {
        priceService.changeStrategy("percentage");
        assertEquals(900.0, priceService.calculatePrice(1000.0)); // 10% 할인
    }
    
    @Test
    public void testFixedAmountDiscountStrategy() {
        priceService.changeStrategy("fixed");
        assertEquals(9000.0, priceService.calculatePrice(10000.0)); // 1000원 할인
    }
    
    @Test
    public void testStrategyChange() {
        priceService.changeStrategy("none");
        assertEquals(1000.0, priceService.calculatePrice(1000.0));
        
        priceService.changeStrategy("percentage");
        assertEquals(900.0, priceService.calculatePrice(1000.0));
        
        priceService.changeStrategy("fixed");
        assertEquals(0.0, priceService.calculatePrice(1000.0)); // 1000원 할인하면 0원
    }
}
