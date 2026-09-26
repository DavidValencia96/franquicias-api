# 🚀 API REST Reactiva de Franquicias

API REST reactiva de alto rendimiento desarrollada con **Spring Boot** y **WebFlux** para la gestión de franquicias, sucursales y control de inventario de productos con persistencia NoSQL.

> 🤖 **Nota de desarrollo:** Este proyecto fue diseñado, desarrollado y optimizado con el apoyo de **Gemini (Google AI)** mediante suscripción profesional personal como herramienta de asistencia técnica y arquitectura.

---

## ⭐ Puntos Extra (Plus) Cumplidos

* [x] **Empaquetado de la aplicación con Docker:** Multi-stage build para una imagen optimizada.
* [x] **Programación Funcional y Reactiva:** Desarrollo basado en Spring WebFlux y Project Reactor (`Mono` / `Flux`).
* [x] **Persistencia de Datos NoSQL:** Integración con MongoDB mediante drivers reactivos.

---

## 🛠️ Tecnologías y Versiones Utilizadas

| Tecnología                       | Versión | Descripción                           |
| :------------------------------- | :------ | :------------------------------------ |
| **Java**                         | `17`    | JDK (Eclipse Temurin)                 |
| **Spring Boot**                  | `3.2.3` | Framework principal de Java           |
| **Spring WebFlux**               | `3.2.3` | Programación reactiva no bloqueante   |
| **Spring Data Reactive MongoDB** | `3.2.3` | Persistencia NoSQL reactiva           |
| **MongoDB**                      | `7.0+`  | Base de datos NoSQL                   |
| **Maven**                        | `3.9+`  | Gestor de dependencias y construcción |
| **Docker Engine / Desktop**      | `24.0+` | Contenerización de la aplicación      |
| **Docker Compose**               | `v2.x`  | Orquestación de servicios             |

---

## 📋 Requisitos Previos e Instalación

1. **[Docker Desktop](https://www.docker.com/products/docker-desktop/)** (incluye Docker Engine y Docker Compose).
2. **[Git](https://git-scm.com/)** para la gestión del repositorio.

---

## 🚀 Despliegue Local con Docker

Para construir las imágenes, inicializar MongoDB y levantar la API en un solo comando:

```bash
# 1. Clonar el repositorio
git clone <(https://github.com/DavidValencia96/franquicias-api)>

# 2. Navegar a la carpeta del proyecto
cd franquicias-api

# 3. Construir y levantar los contenedores
docker-compose up --build -d
```

### 🔧 Comandos útiles

Una vez levantados los servicios, puedes utilizar los siguientes comandos para consultar el estado de los contenedores, revisar los logs de la aplicación o detener completamente los servicios.

**Ver el estado de los contenedores:**

```bash
docker-compose ps
```

**Ver logs de la aplicación en tiempo real:**

```bash
docker-compose logs -f app
```

**Detener los servicios y eliminar los volúmenes:**

```bash
docker-compose down --volumes
```

---

## 📌 Documentación de Endpoints (API REST)

**Base URL:** `http://localhost:8080`

| **Método** | **Endpoint**                                                    | **Descripción**                                  | **Requerimiento** |
| :--------- | :-------------------------------------------------------------- | :----------------------------------------------- | :---------------- |
| `POST`     | `/api/franquicias`                                              | Agregar una nueva franquicia                     | Criterio 2        |
| `POST`     | `/api/franquicias/{id}/sucursales`                              | Agregar una sucursal a una franquicia            | Criterio 3        |
| `POST`     | `/api/franquicias/{fId}/sucursales/{sId}/productos`             | Agregar un producto a una sucursal               | Criterio 4        |
| `DELETE`   | `/api/franquicias/{fId}/sucursales/{sId}/productos/{pId}`       | Eliminar un producto                             | Criterio 5        |
| `PUT`      | `/api/franquicias/{fId}/sucursales/{sId}/productos/{pId}/stock` | Modificar el stock de un producto                | Criterio 6        |
| `GET`      | `/api/franquicias/{fId}/productos-max-stock`                    | Consultar el producto con más stock por sucursal | Criterio 7        |

---
