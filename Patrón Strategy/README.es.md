# Implementación del Patrón Strategy en Spring Boot

Este proyecto demuestra la implementación del Patrón Strategy (Estrategia) en una aplicación Spring Boot. El Patrón Strategy es un patrón de diseño de comportamiento que permite seleccionar un algoritmo en tiempo de ejecución. En lugar de implementar un único algoritmo directamente, el código recibe instrucciones en tiempo de ejecución sobre cuál de una familia de algoritmos usar.

## Planteamiento del Problema

Implementar un sistema de procesamiento de pagos que pueda manejar diferentes métodos de pago (tarjeta de crédito, PayPal, transferencia bancaria) de manera flexible que permita:
- Añadir nuevos métodos de pago sin cambiar el código existente
- Cambiar entre métodos de pago en tiempo de ejecución
- Mantener la lógica de procesamiento de pagos separada del código cliente

## Solución: Patrón Strategy

El Patrón Strategy se implementa con los siguientes componentes:

1. **Interfaz Strategy**: `PaymentStrategy` define el contrato para todas las estrategias de pago
2. **Estrategias Concretas**: 
   - `CreditCardPaymentStrategy`
   - `PayPalPaymentStrategy`
   - `BankTransferPaymentStrategy`
3. **Contexto**: `PaymentProcessor` mantiene una referencia a una estrategia y delega el procesamiento del pago a ella

## Estructura del Proyecto

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

## Cómo Funciona

1. La interfaz `PaymentStrategy` define los métodos que todas las estrategias concretas deben implementar:
   - `processPayment(double amount)`: Procesa un pago con la cantidad dada
   - `getMethodName()`: Obtiene el nombre del método de pago

2. Las clases de estrategia concretas implementan la interfaz `PaymentStrategy` con lógica específica de procesamiento de pagos.

3. La clase `PaymentProcessor` (contexto) mantiene una colección de todas las estrategias de pago disponibles y permite:
   - Establecer la estrategia actual por nombre
   - Procesar pagos utilizando la estrategia actual
   - Obtener una lista de todos los métodos de pago disponibles

4. La inyección de dependencias de Spring se utiliza para inyectar automáticamente todas las estrategias de pago disponibles en el `PaymentProcessor`.

5. El `PaymentDemoRunner` demuestra el patrón en acción:
   - Mostrando todos los métodos de pago disponibles
   - Procesando un pago con la estrategia predeterminada
   - Cambiando a cada una de las otras estrategias y procesando un pago con cada una

## Beneficios del Patrón Strategy

1. **Principio Abierto/Cerrado**: Puedes añadir nuevos métodos de pago sin modificar el código existente
2. **Encapsulación**: La lógica de cada método de pago está encapsulada en su propia clase
3. **Flexibilidad en Tiempo de Ejecución**: El método de pago puede cambiarse en tiempo de ejecución
4. **Elimina Declaraciones Condicionales**: Evita lógica condicional compleja para diferentes métodos de pago
5. **Testabilidad**: Cada estrategia puede ser probada independientemente

## Ejecutando la Aplicación

Para ejecutar la aplicación:

```bash
./mvnw spring-boot:run
```

La aplicación mostrará la demostración del Patrón Strategy en la salida de la consola.

## Integración con Spring Boot

Esta implementación aprovecha las características de Spring Boot:

1. **Escaneo de Componentes**: Las estrategias se descubren y registran automáticamente como beans
2. **Inyección de Dependencias**: Todas las estrategias se inyectan en el contexto
3. **CommandLineRunner**: Se utiliza para demostrar el patrón cuando la aplicación se inicia

## Conclusión

El Patrón Strategy proporciona una forma limpia y flexible de implementar diferentes métodos de pago en una aplicación Spring Boot. Sigue los principios SOLID y hace que el código sea más mantenible y extensible.

## Cómo Añadir un Nuevo Método de Pago

Para añadir un nuevo método de pago, simplemente crea una nueva clase que implemente la interfaz `PaymentStrategy`:

```java
@Component
public class CryptoPaymentStrategy implements PaymentStrategy {

    @Override
    public String processPayment(double amount) {
        return String.format("Procesando pago con criptomonedas de $%.2f", amount);
    }

    @Override
    public String getMethodName() {
        return "Criptomoneda";
    }
}
```

Spring automáticamente detectará y registrará esta nueva estrategia, y estará disponible para su uso sin necesidad de modificar ningún código existente.