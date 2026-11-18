package modelo;

import java.util.Collection;
import java.util.HashMap;

public class BibliotecaModelo {
    private HashMap<String, Libro> libros;

    public BibliotecaModelo() {
        this.libros = new HashMap<>();
    }

    public boolean agregarLibro(Libro libro) {
        if (libros.containsKey(libro.obtenerIsbn())) {
            return false;
        }
        libros.put(libro.obtenerIsbn(), libro);
        return true;
    }

    public Libro buscarLibroPorIsbn(String isbn) {
        return libros.get(isbn);
    }

    public boolean existeLibroPorIsbn(String isbn) {
        return libros.containsKey(isbn);
    }

    public boolean actualizarLibro(String isbn, String nuevoTitulo, String nuevoAutor, int nuevoAnio) {
        Libro libro = libros.get(isbn);
        if (libro == null) {
            return false;
        }
        libro.establecerTitulo(nuevoTitulo);
        libro.establecerAutor(nuevoAutor);
        libro.establecerAnioPublicacion(nuevoAnio);
        return true;
    }

    public boolean eliminarLibroPorIsbn(String isbn) {
        return libros.remove(isbn) != null;
    }

    public Collection<Libro> obtenerLibros() {
        return libros.values();
    }

    public boolean estaVacia() {
        return libros.isEmpty();
    }
}
