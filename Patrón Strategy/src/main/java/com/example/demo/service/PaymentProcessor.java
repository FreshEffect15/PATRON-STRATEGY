package com.example.demo.service;

import com.example.demo.strategy.PaymentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Context class for the Strategy Pattern.
 * This class maintains references to all available payment strategies and
 * allows clients to select and use a specific strategy at runtime.
 */
@Service
public class PaymentProcessor {

    private final Map<String, PaymentStrategy> paymentStrategies = new HashMap<>();
    private PaymentStrategy currentStrategy;

    /**
     * Constructor that injects all available PaymentStrategy beans.
     * Spring will automatically find all beans that implement PaymentStrategy
     * and inject them into this constructor.
     *
     * @param strategies List of all available payment strategies
     */
    @Autowired
    public PaymentProcessor(List<PaymentStrategy> strategies) {
        // Initialize the map of strategies
        for (PaymentStrategy strategy : strategies) {
            paymentStrategies.put(strategy.getMethodName().toLowerCase(), strategy);
        }

        // Set a default strategy if available
        if (!strategies.isEmpty()) {
            currentStrategy = strategies.get(0);
        }
    }

    /**
     * Set the payment strategy to use.
     *
     * @param methodName The name of the payment method to use
     * @return true if the strategy was found and set, false otherwise
     */
    public boolean setPaymentMethod(String methodName) {
        PaymentStrategy strategy = paymentStrategies.get(methodName.toLowerCase());
        if (strategy != null) {
            currentStrategy = strategy;
            return true;
        }
        return false;
    }

    /**
     * Process a payment using the current strategy.
     *
     * @param amount The amount to process
     * @return The result of the payment processing
     * @throws IllegalStateException if no strategy is set
     */
    public String processPayment(double amount) {
        if (currentStrategy == null) {
            throw new IllegalStateException("No se ha seleccionado ningún método de pago");
        }
        return currentStrategy.processPayment(amount);
    }

    /**
     * Get a list of all available payment methods.
     *
     * @return A list of payment method names
     */
    public List<String> getAvailablePaymentMethods() {
        return paymentStrategies.values().stream()
                .map(PaymentStrategy::getMethodName)
                .toList();
    }

    /**
     * Get the current payment method name.
     *
     * @return The name of the current payment method, or null if none is set
     */
    public String getCurrentPaymentMethod() {
        return currentStrategy != null ? currentStrategy.getMethodName() : null;
    }
}
