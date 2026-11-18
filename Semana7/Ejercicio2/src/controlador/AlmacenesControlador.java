package controlador;

import modelo.AlmacenesModelo;
import vista.AlmacenesVista;

public class AlmacenesControlador {
    private AlmacenesModelo modelo;
    private AlmacenesVista vista;

    public AlmacenesControlador(AlmacenesModelo modelo, AlmacenesVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        vista.mostrarMenuPrincipal();
    }

    public void guardarVentas(int[][] ventas) {
        modelo.establecerVentas(ventas);
    }

    public void guardarPrecios(double[] precios) {
        modelo.establecerPrecios(precios);
    }

    public boolean calcularIngresosTotales() {
        return modelo.calcularIngresosTotales();
    }

    public boolean estanVentasLeidas() {
        return modelo.estanVentasLeidas();
    }

    public boolean estanPreciosLeidos() {
        return modelo.estanPreciosLeidos();
    }

    public boolean estanIngresosCalculados() {
        return modelo.estanIngresosCalculados();
    }

    public int[][] obtenerVentas() {
        return modelo.obtenerVentas();
    }

    public double[] obtenerPrecios() {
        return modelo.obtenerPrecios();
    }

    public double[] obtenerIngresosPorAlmacen() {
        return modelo.obtenerIngresosPorAlmacen();
    }

    public double obtenerIngresosTotales() {
        return modelo.obtenerIngresosTotales();
    }
}