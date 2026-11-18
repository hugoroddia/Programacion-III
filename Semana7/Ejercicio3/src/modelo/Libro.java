package modelo;

public class Libro {
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
}
