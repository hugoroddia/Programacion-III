package controlador;

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
        vista.mostrarMenuPrincipal();
    }

    public void reiniciarPartidos() {
        modelo.reiniciarPartidos();
    }

    public void agregarPartido(String local, String visitante) {
        modelo.agregarPartido(local, visitante);
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