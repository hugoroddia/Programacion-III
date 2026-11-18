package controlador;

import java.util.Collection;
import modelo.BibliotecaModelo;
import modelo.Libro;
import vista.BibliotecaVista;

public class BibliotecaControlador {
    private BibliotecaModelo modelo;
    private BibliotecaVista vista;

    public BibliotecaControlador(BibliotecaModelo modelo, BibliotecaVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        vista.mostrarMenuPrincipal();
    }

    public boolean agregarLibro(Libro libro) {
        return modelo.agregarLibro(libro);
    }

    public Libro buscarLibroPorIsbn(String isbn) {
        return modelo.buscarLibroPorIsbn(isbn);
    }

    public boolean existeLibroPorIsbn(String isbn) {
        return modelo.existeLibroPorIsbn(isbn);
    }

    public boolean actualizarLibro(String isbn, String titulo, String autor, int anio) {
        return modelo.actualizarLibro(isbn, titulo, autor, anio);
    }

    public boolean eliminarLibroPorIsbn(String isbn) {
        return modelo.eliminarLibroPorIsbn(isbn);
    }

    public Collection<Libro> obtenerLibros() {
        return modelo.obtenerLibros();
    }

    public boolean estaVacia() {
        return modelo.estaVacia();
    }
}