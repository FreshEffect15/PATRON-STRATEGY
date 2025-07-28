package com.example.demo.strategy.impl;

import com.example.demo.strategy.PaymentStrategy;
import org.springframework.stereotype.Component;

/**
 * Concrete implementation of PaymentStrategy for credit card payments.
 */
@Component
public class CreditCardPaymentStrategy implements PaymentStrategy {

    @Override
    public String processPayment(double amount) {
        // In a real application, this would include credit card processing logic
        return String.format("Procesando pago con tarjeta de crédito de $%.2f", amount);
    }

    @Override
    public String getMethodName() {
        return "Tarjeta de Crédito";
    }
}
