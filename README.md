# MicroServicios
Plataforma de videojuegos desarrollada con arquitectura de microservicios usando Spring Boot.

El sistema permite gestionar usuarios, juegos, biblioteca, pagos, descuentos, catálogo, mods, amigos, notificaciones y facturación. Cada microservicio funciona de manera independiente y se comunica mediante Feign Client, permitiendo una estructura escalable, modular y fácil de mantener. Este trabajo fue creado por Sofia Doenitz, Noelia Maldonado y Matias Rojas

Tecnologías utilizadas:
- Java
- Spring Boot
- Spring Data JPA
- MySQL
- OpenFeign
- Maven
USUARIO        -> 8080
JUEGO          -> 8082
DESCUENTO      -> 8089
CATALOGO       -> 8086
MOD            -> 8087
PAGO           -> 8083
FACTORIZACION  -> 8084
BIBLIOTECA     -> 8081
AMIGOS         -> 8088
NOTIFICACION   -> 8090
