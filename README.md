# Proyecto Fullstack Épico - Microservicios

## Descripción
Este proyecto corresponde a una aplicación desarrollada con arquitectura de microservicios utilizando Spring Boot, Spring Data JPA, Eureka Server, MySQL y Swagger.

El sistema está dividido en distintos servicios independientes, donde cada uno se encarga de una funcionalidad específica relacionada con la gestión de usuarios, juegos, pagos, descuentos, biblioteca y otros módulos del sistema.

## ¿Cómo funciona?

La aplicación utiliza una arquitectura de microservicios:

1. Cada microservicio posee su propia base de datos MySQL.
2. Todos los servicios se registran en Eureka Server para poder descubrirse entre sí.
3. Cada servicio expone una API REST.
4. La documentación de las API se encuentra disponible mediante Swagger.
5. Flyway se utiliza para administrar las migraciones de las bases de datos.
6. Hibernate y JPA permiten la persistencia de los datos.

## Flujo general del sistema

- El usuario realiza una petición a un microservicio.
- El servicio procesa la información y, si es necesario, se comunica con otros microservicios.
- Los datos se almacenan en la base de datos correspondiente.
- La respuesta es devuelta al cliente mediante una API REST.

## Microservicios del proyecto

### ms-usuario (Puerto 8080)
Gestiona el registro y administración de usuarios del sistema.

### ms-amigos (Puerto 8081)
Permite administrar las relaciones de amistad entre usuarios.

### ms-biblioteca (Puerto 8082)
Administra la biblioteca de juegos asociados a cada usuario.

### ms-catalogo (Puerto 8083)
Gestiona el catálogo de juegos disponibles en la plataforma.

### ms-descuento (Puerto 8084)
Administra descuentos y promociones aplicables a los juegos.

### ms-factorizacion (Puerto 8085)
Gestiona procesos de facturación o generación de registros relacionados con compras.

### ms-juego (Puerto 8086)
Administra la información principal de los juegos y realiza integraciones con otros microservicios para obtener información completa.

### ms-mod (Puerto 8087)
Gestiona los mods o complementos asociados a los juegos.

### ms-notificacion (Puerto 8088)
Administra el envío y registro de notificaciones del sistema.

### ms-pago (Puerto 8089)
Gestiona los pagos y transacciones realizadas por los usuarios.

### Eureka Server (Puerto 8761)
Servidor de descubrimiento de servicios encargado de registrar y monitorear todos los microservicios.

## Puertos utilizados

| Servicio | Puerto |
|-----------|---------|
| ms-usuario | 8080 |
| ms-amigos | 8081 |
| ms-biblioteca | 8082 |
| ms-catalogo | 8083 |
| ms-descuento | 8084 |
| ms-factorizacion | 8085 |
| ms-juego | 8086 |
| ms-mod | 8087 |
| ms-notificacion | 8088 |
| ms-pago | 8089 |
| Eureka Server | 8761 |

## Tecnologías utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Spring Cloud Netflix Eureka
- MySQL
- Flyway
- Maven
- Swagger / OpenAPI

## Ejecución del proyecto

1. Iniciar MySQL.
2. Ejecutar primero el servidor Eureka.
3. Levantar los microservicios uno por uno.
4. Verificar que todos aparezcan registrados en:

http://localhost:8761/

5. Acceder a la documentación de cada servicio:

http://localhost:{puerto}/doc/swagger-ui.html

## Bases de datos utilizadas

- db_usuario
- db_amigos
- db_biblioteca
- db_catalogo
- db_descuento
- db_factorizacion
- db_juego
- db_mod
- db_notificacion
- db_pago

## Arquitectura

Cliente → API REST → Microservicios → Bases de Datos MySQL

Todos los servicios son independientes, escalables y se comunican mediante el descubrimiento de servicios proporcionado por Eureka Server.
