package controlador;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import modelo.Factura;
import modelo.FacturasModelo;
import vista.FacturasVista;

public class FacturasControlador {
    private FacturasModelo modelo;
    private FacturasVista vista;

    public FacturasControlador(FacturasModelo modelo, FacturasVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        boolean cargadas = modelo.cargarFacturasTsv();
        vista.mostrarEstadoCarga(cargadas);
        vista.mostrarMenuPrincipal();
    }

    public Path obtenerRutaTsv() {
        return modelo.obtenerRutaTsv();
    }

    public ArrayList<Factura> obtenerFacturas() {
        return modelo.obtenerFacturas();
    }

    public boolean hayFacturas() {
        return modelo.hayFacturas();
    }

    public ArrayList<Factura> filtrarFacturasMayoresQue(double minimo) {
        return modelo.filtrarFacturasMayoresQue(minimo);
    }

    public Path exportarAHtml() throws IOException {
        return modelo.exportarAHtml();
    }

    public Path exportarACsv() throws IOException {
        return modelo.exportarACsv();
    }
}