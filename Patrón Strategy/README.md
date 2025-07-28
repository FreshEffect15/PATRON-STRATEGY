# Strategy Pattern Implementation in Spring Boot

This project demonstrates the implementation of the Strategy Pattern in a Spring Boot application. The Strategy Pattern is a behavioral design pattern that enables selecting an algorithm at runtime. Instead of implementing a single algorithm directly, code receives run-time instructions as to which in a family of algorithms to use.

## Problem Statement

Implement a payment processing system that can handle different payment methods (credit card, PayPal, bank transfer) in a flexible way that allows:
- Adding new payment methods without changing existing code
- Switching between payment methods at runtime
- Keeping payment processing logic separate from the client code

## Solution: Strategy Pattern

The Strategy Pattern is implemented with the following components:

1. **Strategy Interface**: `PaymentStrategy` defines the contract for all payment strategies
2. **Concrete Strategies**: 
   - `CreditCardPaymentStrategy`
   - `PayPalPaymentStrategy`
   - `BankTransferPaymentStrategy`
3. **Context**: `PaymentProcessor` maintains a reference to a strategy and delegates the payment processing to it

## Project Structure

```
src/main/java/com/example/demo/
├── DemoApplication.java
├── strategy/
│   ├── PaymentStrategy.java
│   └── impl/
│       ├── CreditCardPaymentStrategy.java
│       ├── PayPalPaymentStrategy.java
│       └── BankTransferPaymentStrategy.java
├── service/
│   └── PaymentProcessor.java
└── runner/
    └── PaymentDemoRunner.java
```

## How It Works

1. The `PaymentStrategy` interface defines the methods that all concrete strategies must implement:
   - `processPayment(double amount)`: Process a payment with the given amount
   - `getMethodName()`: Get the name of the payment method

2. Concrete strategy classes implement the `PaymentStrategy` interface with specific payment processing logic.

3. The `PaymentProcessor` class (context) maintains a collection of all available payment strategies and allows:
   - Setting the current strategy by name
   - Processing payments using the current strategy
   - Getting a list of all available payment methods

4. Spring's dependency injection is used to automatically inject all available payment strategies into the `PaymentProcessor`.

5. The `PaymentDemoRunner` demonstrates the pattern in action by:
   - Displaying all available payment methods
   - Processing a payment with the default strategy
   - Changing to each of the other strategies and processing a payment with each one

## Benefits of the Strategy Pattern

1. **Open/Closed Principle**: You can add new payment methods without modifying existing code
2. **Encapsulation**: Each payment method's logic is encapsulated in its own class
3. **Runtime Flexibility**: The payment method can be changed at runtime
4. **Eliminates Conditional Statements**: Avoids complex conditional logic for different payment methods
5. **Testability**: Each strategy can be tested independently

## Running the Application

To run the application:

```bash
./mvnw spring-boot:run
```

The application will display the demonstration of the Strategy Pattern in the console output.

## Spring Boot Integration

This implementation leverages Spring Boot features:

1. **Component Scanning**: Strategies are automatically discovered and registered as beans
2. **Dependency Injection**: All strategies are injected into the context
3. **CommandLineRunner**: Used to demonstrate the pattern when the application starts

## Conclusion

The Strategy Pattern provides a clean and flexible way to implement different payment methods in a Spring Boot application. It follows SOLID principles and makes the code more maintainable and extensible.