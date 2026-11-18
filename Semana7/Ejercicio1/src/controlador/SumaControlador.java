package controlador;

import modelo.SumaModelo;
import vista.SumaVista;

public class SumaControlador {
    private SumaModelo modelo;
    private SumaVista vista;

    public SumaControlador(SumaModelo modelo, SumaVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        vista.mostrarMenuPrincipal();
    }

    public void guardarNumeros(int numero1, int numero2) {
        modelo.establecerNumeros(numero1, numero2);
    }

    public boolean calcularSuma() {
        return modelo.calcularSuma();
    }

    public boolean estanNumerosLeidos() {
        return modelo.estanNumerosLeidos();
    }

    public boolean estaSumaCalculada() {
        return modelo.estaSumaCalculada();
    }

    public int obtenerNumero1() {
        return modelo.obtenerNumero1();
    }

    public int obtenerNumero2() {
        return modelo.obtenerNumero2();
    }

    public int obtenerResultado() {
        return modelo.obtenerResultado();
    }
}