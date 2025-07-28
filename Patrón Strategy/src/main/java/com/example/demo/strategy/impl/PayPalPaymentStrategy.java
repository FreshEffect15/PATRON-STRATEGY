package com.example.demo.strategy.impl;

import com.example.demo.strategy.PaymentStrategy;
import org.springframework.stereotype.Component;

/**
 * Concrete implementation of PaymentStrategy for PayPal payments.
 */
@Component
public class PayPalPaymentStrategy implements PaymentStrategy {

    @Override
    public String processPayment(double amount) {
        // In a real application, this would include PayPal API integration
        return String.format("Procesando pago con PayPal de $%.2f", amount);
    }

    @Override
    public String getMethodName() {
        return "PayPal";
    }
}
