package controlador;

import java.nio.file.Path;
import java.util.ArrayList;
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
        try {
            boolean cargado = modelo.cargarDesdeBinario();
            vista.mostrarMensajeCargaInicial(cargado, null);
        } catch (Exception e) {
            vista.mostrarMensajeCargaInicial(false, e.getMessage());
        }

        vista.mostrarMenuPrincipal();

        try {
            modelo.guardarEnBinario();
            vista.mostrarMensajeGuardadoFinal(true, modelo.obtenerRutaEscritorio("libros.bin").toString());
        } catch (Exception e) {
            vista.mostrarMensajeGuardadoFinal(false, e.getMessage());
        }
    }

    public ArrayList<Libro> obtenerLibros() {
        return modelo.obtenerLibros();
    }

    public boolean estaVacia() {
        return modelo.estaVacia();
    }

    public boolean agregarLibro(Libro libro) {
        return modelo.agregarLibro(libro);
    }

    public Libro eliminarLibroPorIndice(int indice) {
        return modelo.eliminarLibroPorIndice(indice);
    }

    public boolean modificarLibroPorIndice(int indice, String nuevoTitulo, String nuevoAutor, int nuevoAnio) {
        return modelo.modificarLibroPorIndice(indice, nuevoTitulo, nuevoAutor, nuevoAnio);
    }

    public Path exportarCsv() throws Exception {
        return modelo.exportarCsv();
    }

    public Path exportarJson() throws Exception {
        return modelo.exportarJson();
    }

    public Path exportarXml() throws Exception {
        return modelo.exportarXml();
    }

    public int importarJson() throws Exception {
        return modelo.importarJson();
    }

    public int importarXml() throws Exception {
        return modelo.importarXml();
    }

    public int importarCsv() throws Exception {
        return modelo.importarCsv();
    }
}