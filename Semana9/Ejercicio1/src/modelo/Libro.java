package modelo;

import java.io.Serializable;
import java.util.Objects;

public class Libro implements Serializable {
    private static final long serialVersionUID = 1L;

    private String isbn;
    private String titulo;
    private String autor;
    private int anioPublicacion;

    public Libro(String isbn, String titulo, String autor, int anioPublicacion) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
    }

    public String obtenerIsbn() {
        return isbn;
    }

    public String obtenerTitulo() {
        return titulo;
    }

    public void establecerTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String obtenerAutor() {
        return autor;
    }

    public void establecerAutor(String autor) {
        this.autor = autor;
    }

    public int obtenerAnioPublicacion() {
        return anioPublicacion;
    }

    public void establecerAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Libro otro = (Libro) obj;
        String isbnActual = (this.isbn == null) ? "" : this.isbn.trim().toLowerCase();
        String isbnOtro = (otro.isbn == null) ? "" : otro.isbn.trim().toLowerCase();
        return Objects.equals(isbnActual, isbnOtro);
    }

    @Override
    public int hashCode() {
        String isbnNormalizado = (this.isbn == null) ? "" : this.isbn.trim().toLowerCase();
        return Objects.hash(isbnNormalizado);
    }
}