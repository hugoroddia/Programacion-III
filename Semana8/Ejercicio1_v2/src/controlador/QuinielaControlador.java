package controlador;

import java.nio.file.Path;
import java.util.ArrayList;
import modelo.Partido;
import modelo.QuinielaModelo;
import vista.QuinielaVista;

public class QuinielaControlador {
    private QuinielaModelo modelo;
    private QuinielaVista vista;

    public QuinielaControlador(QuinielaModelo modelo, QuinielaVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        boolean cargado = modelo.cargarPartidosDesdeFichero();
        vista.mostrarResultadoCarga(cargado);
        vista.mostrarMenuPrincipal();
    }

    public boolean cargarPartidosDesdeFichero() {
        return modelo.cargarPartidosDesdeFichero();
    }

    public Path obtenerRutaFicheroEquipos() {
        return modelo.obtenerRutaFicheroEquipos();
    }

    public ArrayList<String> obtenerErroresCarga() {
        return modelo.obtenerErroresCarga();
    }

    public boolean registrarResultado(int indice, int golesLocal, int golesVisitante) {
        return modelo.registrarResultado(indice, golesLocal, golesVisitante);
    }

    public ArrayList<Partido> obtenerPartidos() {
        return modelo.obtenerPartidos();
    }

    public boolean hayPartidos() {
        return modelo.hayPartidos();
    }
}