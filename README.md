# PRO413_20262_Hito2_Quarkus_ApellidoNombre
## Backend API REST - Gestión de Películas

Proyecto desarrollado para la evaluación **PRO-413 - Programación III (Hito 2: Integración de Frameworks: Quarkus + Laravel)**. Este proyecto implementa un servicio API REST en Quarkus responsable de la gestión del recurso **Películas**, almacenando la información en memoria y registrando mensajes de log en consola para cada operación.

---

### 1. Descripción del proyecto
API REST desacoplada que expone servicios HTTP para consultar (GET) y registrar (POST) películas. Los datos se gestionan en memoria utilizando colecciones sincronizadas en Java, mostrando mensajes en la consola al recibir peticiones desde clientes REST como Laravel, cURL o navegadores web.

---

### 2. Framework y versión utilizada
- **Framework:** Quarkus 3.39.2 (Java REST Jackson)
- **Lenguaje:** Java OpenJDK 21
- **Gestor de Dependencias:** Apache Maven 3.9.x

---

### 3. Requisitos previos
- **Java Development Kit (JDK):** Versión 17 o 21 instalada (`java -version`).
- **Maven:** Versión 3.8.x o superior (`mvn -version`) o uso del wrapper incluido (`./mvnw`).
- **Git:** Para control de versiones (`git --version`).

---

### 4. Instalación de dependencias
Para compilar el proyecto y descargar las dependencias necesarias:

```bash
# Usando Maven local
mvn clean compile

# O usando el wrapper de Maven
./mvnw clean compile
```

---

### 5. Configuración necesaria
El proyecto viene preconfigurado en el archivo `src/main/resources/application.properties`:

```properties
# Puerto y host del servidor REST
quarkus.http.port=8080
quarkus.http.host=0.0.0.0

# Formato de logs en consola
quarkus.log.console.format=%d{yyyy-MM-dd HH:mm:ss} %-5p [%c{1.}] %s%e%n
quarkus.log.level=INFO
quarkus.log.category."com.evaluacion".level=INFO
```

---

### 6. Comando para ejecutar el proyecto
Para iniciar el servidor en modo desarrollo (con hot-reload):

```bash
mvn quarkus:dev
```

O usando el wrapper:

```bash
./mvnw quarkus:dev
```

---

### 7. Puerto utilizado
- **Puerto:** `8080` (HTTP)
- **URL Base:** `http://127.0.0.1:8080` o `http://localhost:8080`

---

### 8. Endpoints disponibles

| Método HTTP | Endpoint | Descripción | Código de Éxito |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/peliculas` | Consulta y lista todas las películas registradas | `200 OK` |
| **POST** | `/api/peliculas` | Registra una nueva película en formato JSON | `201 Created` |
| **GET** | `/api/peliculas/{id}` | Consulta una película específica por su ID | `200 OK` / `404 Not Found` |

---

### 9. Ejemplo de petición

#### A) Petición GET (Consultar catálogo):
```bash
curl -i -X GET http://127.0.0.1:8080/api/peliculas
```

#### B) Petición POST (Registrar película):
```bash
curl -i -X POST http://127.0.0.1:8080/api/peliculas \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Avatar",
    "director": "James Cameron",
    "anio": 2009,
    "genero": "Ciencia Ficción",
    "sinopsis": "En un exuberante planeta alienígena llamado Pandora, un exmarine emprende una misión que lo llevará a liderar a los habitantes locales en una batalla por su supervivencia."
  }'
```

---

### 10. Ejemplo de respuesta

#### A) Respuesta GET (`200 OK`):
```json
[
  {
    "id": 1,
    "titulo": "Inception (El Origen)",
    "director": "Christopher Nolan",
    "anio": 2010,
    "genero": "Ciencia Ficción",
    "sinopsis": "Un ladrón que roba secretos corporativos mediante tecnología de infiltración en sueños."
  },
  {
    "id": 2,
    "titulo": "El Padrino",
    "director": "Francis Ford Coppola",
    "anio": 1972,
    "genero": "Drama / Crimen",
    "sinopsis": "El líder de una influyente familia mafiosa transfiere el control de su imperio a su hijo."
  },
  {
    "id": 3,
    "titulo": "Interstellar",
    "director": "Christopher Nolan",
    "anio": 2014,
    "genero": "Ciencia Ficción / Aventura",
    "sinopsis": "Un grupo de astronautas viaja a través de un agujero de gusano para salvar a la humanidad."
  }
]
```

#### B) Respuesta POST (`201 Created`):
```json
{
  "id": 4,
  "titulo": "Avatar",
  "director": "James Cameron",
  "anio": 2009,
  "genero": "Ciencia Ficción",
  "sinopsis": "En un exuberante planeta alienígena llamado Pandora, un exmarine emprende una misión que lo llevará a liderar a los habitantes locales en una batalla por su supervivencia."
}
```

---

### 11. Forma de comprobar la integración
1. Inicie este servidor Quarkus con `mvn quarkus:dev`.
2. Verifique en consola el mensaje de inicio: `Listening on: http://0.0.0.0:8080`.
3. Inicie el proyecto cliente Laravel en otra terminal (`php artisan serve --port=8000`).
4. Abra su navegador en `http://127.0.0.1:8000/peliculas`.
5. Observe en la consola de Quarkus cómo se imprimen los logs `[QUARKUS LOG] GET /api/peliculas`.
6. Envíe una nueva película desde el formulario web de Laravel y verifique el log `[QUARKUS LOG] POST /api/peliculas` en la consola de Quarkus y la actualización instantánea del catálogo en Laravel.
