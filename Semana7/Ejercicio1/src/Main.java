import controlador.SumaControlador;
import modelo.SumaModelo;
import vista.SumaVista;

public class Main {
    public static void main(String[] args) {
        SumaModelo modelo = new SumaModelo();
        SumaVista vista = new SumaVista();
        SumaControlador controlador = new SumaControlador(modelo, vista);

        vista.establecerControlador(controlador);
        controlador.iniciar();
    }
}