# PATRON-STRATEGY - CONSIGNA
------------------------------
Crear un sistema de notificaciones en Spring Boot aplicando el Patrón Strategy, donde cada canal (email, SMS, push) sea una estrategia concreta anotada con @Component e implemente una interfaz común. El servicio NotificationService gestionará las estrategias y permitirá cambiar dinámicamente el canal de envío. Un controlador REST (NotificationController) ofrecerá endpoints para listar, seleccionar y usar los canales. Además, se incluirá un NotificationDemoRunner para mostrar el funcionamiento al iniciar la app. Se sugiere agregar funcionalidades opcionales (multicanal, prioridad, plantillas, registro en H2) y entregar el código con un README.md, incluyendo explicación del patrón, principios SOLID e instrucciones de uso.

# PATRON-STRATEGY - IDEA

La idea es armar un sistema en Spring Boot que pueda enviar notificaciones por distintos medios (como email, SMS o push) de forma flexible. Usamos el Patrón Strategy para poder elegir el canal que queramos en tiempo de ejecución, sin tener que tocar el código cada vez que sumamos algo nuevo. Todo sigue buenas prácticas (como los principios SOLID), así que es fácil de mantener y extender.

# PATRON-STRATEGY - ¿Cómo está armado?

El sistema se organiza así:

Interfaz Strategy (NotificationStrategy): Define lo que todas las estrategias de notificación tienen que hacer.

Estrategias concretas: Son las clases que implementan la interfaz, cada una para un canal distinto (email, SMS, push).

Servicio principal (NotificationService): Maneja todas las estrategias y se encarga de usar la que esté activa en ese momento.

Controlador REST: Permite interactuar con el sistema desde fuera, usando endpoints para probar, cambiar de canal, etc.

# PATRON-STRATEGY - HERRAMIENTAS

Java 17

Spring Boot 3.x

Spring Web para la API REST

Spring Data JPA (opcional, si querés guardar las notificaciones)

Base de datos H2 (opcional)

Lombok para evitar escribir getters/setters manualmente

Maven para manejar dependencias
