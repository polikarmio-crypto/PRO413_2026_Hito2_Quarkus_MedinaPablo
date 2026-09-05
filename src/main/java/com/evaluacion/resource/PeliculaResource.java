package com.evaluacion.resource;

import com.evaluacion.model.Pelicula;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Recurso REST para la gestión de Películas en Quarkus.
 * Diseñado con nivel de Programación III (básico, limpio y claro).
 */
@Path("/api/peliculas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PeliculaResource {

    private static final Logger LOG = Logger.getLogger(PeliculaResource.class);

    // Almacenamiento temporal en memoria mediante una lista sincronizada
    private static final List<Pelicula> peliculas = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicLong contadorId = new AtomicLong(1);

    // Bloque de inicialización estática con películas de ejemplo
    static {
        peliculas.add(new Pelicula(
                contadorId.getAndIncrement(),
                "Inception (El Origen)",
                "Christopher Nolan",
                2010,
                "Ciencia Ficción",
                "Un ladrón que roba secretos corporativos mediante tecnología de infiltración en sueños."
        ));

        peliculas.add(new Pelicula(
                contadorId.getAndIncrement(),
                "El Padrino",
                "Francis Ford Coppola",
                1972,
                "Drama / Crimen",
                "El líder de una influyente familia mafiosa transfiere el control de su imperio a su hijo."
        ));

        peliculas.add(new Pelicula(
                contadorId.getAndIncrement(),
                "Interstellar",
                "Christopher Nolan",
                2014,
                "Ciencia Ficción / Aventura",
                "Un grupo de astronautas viaja a través de un agujero de gusano para salvar a la humanidad."
        ));
    }

    /**
     * Operación 1: GET para consultar todas las películas.
     * Endpoint: GET /api/peliculas
     */
    @GET
    public Response obtenerPeliculas() {
        // Mensaje de log en consola para evidenciar la ejecución
        String mensajeLog = String.format("[QUARKUS LOG] GET /api/peliculas - Se consultaron %d películas registradas.", peliculas.size());
        System.out.println(mensajeLog);
        LOG.info(mensajeLog);

        return Response.ok(peliculas).build();
    }

    /**
     * Operación 2: POST para registrar una nueva película.
     * Endpoint: POST /api/peliculas
     */
    @POST
    public Response registrarPelicula(Pelicula nuevaPelicula) {
        if (nuevaPelicula == null || nuevaPelicula.getTitulo() == null || nuevaPelicula.getTitulo().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"El título de la película es obligatorio.\"}")
                    .build();
        }

        // Asignar ID autoincremental si no fue provisto
        if (nuevaPelicula.getId() == null || nuevaPelicula.getId() <= 0) {
            nuevaPelicula.setId(contadorId.getAndIncrement());
        }

        // Guardar en la lista en memoria
        peliculas.add(nuevaPelicula);

        // Mensaje de log en consola para evidenciar la ejecución
        String mensajeLog = String.format("[QUARKUS LOG] POST /api/peliculas - Nueva película registrada: ID=%d, Título='%s', Director='%s', Año=%d, Género='%s'",
                nuevaPelicula.getId(),
                nuevaPelicula.getTitulo(),
                nuevaPelicula.getDirector(),
                nuevaPelicula.getAnio(),
                nuevaPelicula.getGenero());

        System.out.println(mensajeLog);
        LOG.info(mensajeLog);

        // Retorna HTTP 201 Created con el objeto creado en formato JSON
        return Response.status(Response.Status.CREATED).entity(nuevaPelicula).build();
    }

    /**
     * Operación adicional: GET por ID para consultar una película específica.
     * Endpoint: GET /api/peliculas/{id}
     */
    @GET
    @Path("/{id}")
    public Response obtenerPeliculaPorId(@PathParam("id") Long id) {
        for (Pelicula p : peliculas) {
            if (p.getId().equals(id)) {
                String mensajeLog = String.format("[QUARKUS LOG] GET /api/peliculas/%d - Película encontrada: '%s'", id, p.getTitulo());
                System.out.println(mensajeLog);
                LOG.info(mensajeLog);
                return Response.ok(p).build();
            }
        }

        String mensajeLog = String.format("[QUARKUS LOG] GET /api/peliculas/%d - Película no encontrada (404).", id);
        System.out.println(mensajeLog);
        LOG.warn(mensajeLog);

        return Response.status(Response.Status.NOT_FOUND)
                .entity("{\"error\": \"Película no encontrada con ID " + id + "\"}")
                .build();
    }
}
