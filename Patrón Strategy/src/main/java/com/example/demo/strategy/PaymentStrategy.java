package com.example.demo.strategy;

/**
 * Strategy interface for different payment methods.
 * This is the core of the Strategy Pattern - defining a family of algorithms.
 */
public interface PaymentStrategy {
    
    /**
     * Process a payment with the given amount.
     * 
     * @param amount The amount to be processed
     * @return A message indicating the result of the payment processing
     */
    String processPayment(double amount);
    
    /**
     * Get the name of the payment method.
     * 
     * @return The name of the payment method
     */
    String getMethodName();
}