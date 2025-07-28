package com.example.demo.controller;

import com.example.demo.service.PaymentProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST controller to demonstrate the Strategy Pattern in action.
 */
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentProcessor paymentProcessor;

    @Autowired
    public PaymentController(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    /**
     * Get all available payment methods.
     *
     * @return List of payment method names
     */
    @GetMapping("/methods")
    public ResponseEntity<List<String>> getPaymentMethods() {
        return ResponseEntity.ok(paymentProcessor.getAvailablePaymentMethods());
    }

    /**
     * Get the current payment method.
     *
     * @return The current payment method name
     */
    @GetMapping("/current-method")
    public ResponseEntity<String> getCurrentMethod() {
        String currentMethod = paymentProcessor.getCurrentPaymentMethod();
        if (currentMethod == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(currentMethod);
    }

    /**
     * Set the payment method to use.
     *
     * @param methodName The name of the payment method
     * @return Success or error message
     */
    @PostMapping("/set-method")
    public ResponseEntity<Map<String, String>> setPaymentMethod(@RequestParam String methodName) {
        boolean success = paymentProcessor.setPaymentMethod(methodName);
        if (success) {
            return ResponseEntity.ok(Map.of("message", "Payment method set to " + methodName));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid payment method: " + methodName));
        }
    }

    /**
     * Process a payment with the current payment method.
     *
     * @param amount The amount to process
     * @return The result of the payment processing
     */
    @PostMapping("/process")
    public ResponseEntity<Map<String, String>> processPayment(@RequestParam double amount) {
        try {
            String result = paymentProcessor.processPayment(amount);
            return ResponseEntity.ok(Map.of(
                "message", result,
                "method", paymentProcessor.getCurrentPaymentMethod()
            ));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}