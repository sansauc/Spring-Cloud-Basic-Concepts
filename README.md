# Spring Cloud Basic Concepts

Proyecto práctico de microservicios con **Spring Boot** y **Spring Cloud**. Un catálogo y un servicio de productos que se descubren entre sí, leen su configuración de un servidor central y se exponen a través de un único API Gateway.

## Arquitectura

```
                    ┌──────────────────┐
   Cliente ───────► │   api-gateway    │ :8060  (WebFlux)
                    └────────┬─────────┘
                             │ lb://
              ┌──────────────┴──────────────┐
              ▼                             ▼
    ┌──────────────────┐  OpenFeign  ┌──────────────────┐
    │  service-product │────────────►│ service-catalogo │
    │      :8081       │             │      :8082       │
    └──────────────────┘             └──────────────────┘

    service-registry :8761 (Eureka)    service-config :8088 (Config Server) ← todos los servicios se registran / leen su configuración aquí
```

| Módulo | Puerto | Descripción |
|---|---|---|
| `service-registry` | 8761 | Servidor Eureka (service discovery) |
| `service-config` | 8088 | Spring Cloud Config Server (perfil `native`) |
| `service-catalogo` | 8082 | CRUD de catálogos (JPA + PostgreSQL) |
| `service-product` | 8081 | CRUD de productos; consulta el catálogo vía OpenFeign |
| `api-gateway` | 8060 | Punto de entrada único, enruta con `lb://` usando Eureka |

## Tecnologías

- Java 21
- Spring Boot 4.1.1 y Spring Cloud 2025.1.3
- Eureka, Config Server, Spring Cloud Gateway (WebFlux), OpenFeign, LoadBalancer
- Spring Data JPA + PostgreSQL (Supabase)
- Maven y Lombok

## Configuración centralizada

Los archivos de configuración de cada servicio están en
[`service-config/src/main/resources/services-configs/`](service-config/src/main/resources/services-configs/)
(`api-gateway.yml`, `service-catalogo.yml`, `service-product.yml`). Cada microservicio solo trae su nombre y la
dirección del Config Server en su `application.yml`.

## Variables de entorno

Las credenciales de base de datos no están en el repositorio. Crea un archivo `.env` en la raíz del proyecto (ya está en el `.gitignore`):

```properties
CATALOGO_DB_URL=jdbc:postgresql://<host>:5432/<db>
CATALOGO_DB_USERNAME=
CATALOGO_DB_PASSWORD=

PRODUCT_DB_URL=jdbc:postgresql://<host>:5432/<db>
PRODUCT_DB_USERNAME=
PRODUCT_DB_PASSWORD=
```

`service-catalogo` y `service-product` lo leen al arrancar (`./.env` o `../.env`).

## Cómo ejecutarlo

Requisitos: JDK 21 y una base de datos PostgreSQL accesible.

Levanta los módulos **en este orden**, cada uno desde su carpeta (o desde tu IDE):

```bash
./mvnw spring-boot:run   # 1. service-registry
./mvnw spring-boot:run   # 2. service-config
./mvnw spring-boot:run   # 3. service-catalogo
./mvnw spring-boot:run   # 4. service-product
./mvnw spring-boot:run   # 5. api-gateway
```

Cuando termines, el panel de Eureka en <http://localhost:8761> debería mostrar los tres servicios registrados.

## Endpoints

Todo se consume a través del gateway (`http://localhost:8060`):

| Método | Ruta | Descripción |
|---|---|---|
| GET | `/api/v1/catalogs` | Lista los catálogos |
| GET | `/api/v1/catalogs/{id}` | Catálogo por id |
| GET | `/api/v1/catalogs/name/{name}` | Catálogo por nombre |
| POST | `/api/v1/catalogs` | Crea un catálogo |
| GET | `/api/v1/products` | Lista los productos |
| GET | `/api/v1/products/{id}` | Producto por id, con el detalle de su catálogo |
| GET | `/api/v1/products/name/{name}` | Producto por nombre |
| POST | `/api/v1/products` | Crea un producto |

Ejemplo:

```bash
curl http://localhost:8060/api/v1/products
```

## Qué se practica aquí

- Registro y descubrimiento de servicios con Eureka
- Configuración centralizada con Config Server
- Enrutamiento y balanceo de carga con API Gateway
- Comunicación entre microservicios con OpenFeign
