package com.evaluacion.model;

/**
 * Modelo que representa el recurso de Película.
 * Conocimientos básicos de Programación III (POJO con atributos, constructores y getters/setters).
 */
public class Pelicula {
    private Long id;
    private String titulo;
    private String director;
    private int anio;
    private String genero;
    private String sinopsis;

    // Constructor vacío requerido para la deserialización JSON (Jackson)
    public Pelicula() {
    }

    // Constructor con parámetros
    public Pelicula(Long id, String titulo, String director, int anio, String genero, String sinopsis) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.anio = anio;
        this.genero = genero;
        this.sinopsis = sinopsis;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", director='" + director + '\'' +
                ", anio=" + anio +
                ", genero='" + genero + '\'' +
                ", sinopsis='" + sinopsis + '\'' +
                '}';
    }
}
