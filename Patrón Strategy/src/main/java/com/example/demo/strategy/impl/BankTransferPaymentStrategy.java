package com.example.demo.strategy.impl;

import com.example.demo.strategy.PaymentStrategy;
import org.springframework.stereotype.Component;

/**
 * Concrete implementation of PaymentStrategy for bank transfer payments.
 */
@Component
public class BankTransferPaymentStrategy implements PaymentStrategy {

    @Override
    public String processPayment(double amount) {
        // In a real application, this would include bank transfer processing logic
        return String.format("Procesando pago por transferencia bancaria de $%.2f", amount);
    }

    @Override
    public String getMethodName() {
        return "Transferencia Bancaria";
    }
}
