package com.github.alberto.bibliotecajdbc.model;

import java.io.Serializable;
import java.util.Date;

public class Libro implements Serializable {
    private String titulo;
    private String autor;
    private Date fecha_publicacion;

    public Libro() {
    }

    public Libro(String titulo, String autor, Date fecha_publicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.fecha_publicacion = fecha_publicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(Date fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    @Override
    public String toString() {
        return "libro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", fecha_publicacion=" + fecha_publicacion +
                '}';
    }
}
