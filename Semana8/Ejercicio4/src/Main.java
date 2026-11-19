import controlador.ListaClaseControlador;
import modelo.ListaClaseModelo;
import vista.ListaClaseVista;

public class Main {
    public static void main(String[] args) {
        ListaClaseModelo modelo = new ListaClaseModelo();
        ListaClaseVista vista = new ListaClaseVista();
        ListaClaseControlador controlador = new ListaClaseControlador(modelo, vista);

        vista.establecerControlador(controlador);
        controlador.iniciar();
    }
}