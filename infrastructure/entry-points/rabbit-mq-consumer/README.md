# RabbitMQ Consumer

Este módulo contiene el consumer de RabbitMQ para escuchar eventos del sistema.

## Configuración

Para habilitar el consumer de RabbitMQ, agrega las siguientes propiedades en tu archivo `application.yml`:

```yaml
spring:
  rabbitmq:
    host: localhost
    port: 5672
    username: tu-usuario
    password: tu-password
    virtual-host: /
    queue:
      user-registration: user.registration.queue
    exchange:
      name: nexus.exchange
      routing-key: user.registration
```

## Eventos soportados

### UserRegistrationEvent

Este evento se dispara cuando un nuevo usuario se registra en el sistema.

**Estructura del evento:**

```json
{
  "userId": "uuid",
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "phoneNumber": "string",
  "birthDate": "string"
}
```

**Cola:** `user.registration.queue`

**Handler:** `UserRegistrationEventHandler`

**Acción:** Crea un nuevo usuario en la base de datos del servicio.

## Componentes

- **RabbitMQConfig**: Configuración de RabbitMQ con soporte reactivo
- **RabbitMQProperties**: Propiedades de configuración
- **UserRegistrationConsumer**: Consumer que escucha eventos de registro de usuarios
- **UserRegistrationEventHandler**: Handler que procesa los eventos y ejecuta la lógica de negocio

## Características

- Soporte reactivo con Reactor RabbitMQ
- Auto-acknowledment de mensajes
- Manejo de errores con retry automático
- Logging detallado de eventos
- QoS configurado en 10 mensajes
