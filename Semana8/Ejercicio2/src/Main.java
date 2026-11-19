import controlador.FacturasControlador;
import modelo.FacturasModelo;
import vista.FacturasVista;

public class Main {
    public static void main(String[] args) {
        FacturasModelo modelo = new FacturasModelo();
        FacturasVista vista = new FacturasVista();
        FacturasControlador controlador = new FacturasControlador(modelo, vista);

        vista.establecerControlador(controlador);
        controlador.iniciar();
    }
}