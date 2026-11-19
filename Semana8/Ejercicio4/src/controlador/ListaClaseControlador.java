package controlador;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import modelo.DatosDeAlumno;
import modelo.ListaClaseModelo;
import vista.ListaClaseVista;

public class ListaClaseControlador {
    private ListaClaseModelo modelo;
    private ListaClaseVista vista;

    public ListaClaseControlador(ListaClaseModelo modelo, ListaClaseVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        vista.mostrarMenuPrincipal();
    }

    public void regenerarListaAlumnos() {
        modelo.generarListaAlumnos();
    }

    public ArrayList<DatosDeAlumno> obtenerAlumnos() {
        return modelo.obtenerAlumnos();
    }

    public Path exportarDatosPersonalesCsv() throws IOException {
        return modelo.exportarDatosPersonalesCsv();
    }

    public Path exportarDireccionesCsv() throws IOException {
        return modelo.exportarDireccionesCsv();
    }
}