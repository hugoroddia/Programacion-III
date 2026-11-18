import controlador.BibliotecaControlador;
import modelo.BibliotecaModelo;
import vista.BibliotecaVista;

public class Main {
    public static void main(String[] args) {
        BibliotecaModelo modelo = new BibliotecaModelo();
        BibliotecaVista vista = new BibliotecaVista();
        BibliotecaControlador controlador = new BibliotecaControlador(modelo, vista);

        vista.establecerControlador(controlador);
        controlador.iniciar();
    }
}