import controlador.AlmacenesControlador;
import modelo.AlmacenesModelo;
import vista.AlmacenesVista;

public class Main {
    public static void main(String[] args) {
        AlmacenesModelo modelo = new AlmacenesModelo();
        AlmacenesVista vista = new AlmacenesVista();
        AlmacenesControlador controlador = new AlmacenesControlador(modelo, vista);

        vista.establecerControlador(controlador);
        controlador.iniciar();
    }
}