package com.evaluacion.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
class PeliculaResourceTest {

    @Test
    void testListarPeliculasEndpoint() {
        given()
          .when().get("/api/peliculas")
          .then()
             .statusCode(200)
             .contentType(ContentType.JSON)
             .body("$", notNullValue());
    }

    @Test
    void testRegistrarPeliculaEndpoint() {
        String json = "{"
                + "\"titulo\": \"Matrix\","
                + "\"director\": \"Lana y Lilly Wachowski\","
                + "\"anio\": 1999,"
                + "\"genero\": \"Ciencia Ficción\","
                + "\"sinopsis\": \"Un programador descubre la verdadera naturaleza de su realidad.\""
                + "}";

        given()
          .contentType(ContentType.JSON)
          .body(json)
          .when().post("/api/peliculas")
          .then()
             .statusCode(201)
             .contentType(ContentType.JSON)
             .body("titulo", is("Matrix"))
             .body("director", is("Lana y Lilly Wachowski"));
    }
}