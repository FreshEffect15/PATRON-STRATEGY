package com.example.demo.runner;

import com.example.demo.service.PaymentProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * CommandLineRunner to demonstrate the Strategy Pattern in action.
 * This class will run when the Spring Boot application starts.
 */
@Component
public class PaymentDemoRunner implements CommandLineRunner {

    private final PaymentProcessor paymentProcessor;

    @Autowired
    public PaymentDemoRunner(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    @Override
    public void run(String... args) {
        System.out.println("\n=== Demostración del Patrón Strategy de Pagos ===\n");

        // Display available payment methods
        List<String> methods = paymentProcessor.getAvailablePaymentMethods();
        System.out.println("Métodos de pago disponibles:");
        for (String method : methods) {
            System.out.println("- " + method);
        }
        System.out.println();

        // Process payment with default strategy
        double amount = 100.50;
        System.out.println("Método de pago actual: " + paymentProcessor.getCurrentPaymentMethod());
        System.out.println(paymentProcessor.processPayment(amount));
        System.out.println();

        // Change strategy and process payment again
        for (String method : methods) {
            if (!method.equals(paymentProcessor.getCurrentPaymentMethod())) {
                System.out.println("Cambiando método de pago a: " + method);
                paymentProcessor.setPaymentMethod(method);
                System.out.println(paymentProcessor.processPayment(amount));
                System.out.println();
            }
        }

        System.out.println("=== Fin de la Demostración del Patrón Strategy de Pagos ===\n");
    }
}
