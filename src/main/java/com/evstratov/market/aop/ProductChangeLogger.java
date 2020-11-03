package com.evstratov.market.aop;

import com.evstratov.market.entities.Product;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProductChangeLogger {
    @After(value = "execution(String com.evstratov.market.controllers.ProductController.saveProduct(..))")
    public void logProductChange(JoinPoint joinPoint) {
        Product product = (Product) joinPoint.getArgs()[0];
        System.out.println("Меняем состояние объекта " + product.getTitle());
    }
}
