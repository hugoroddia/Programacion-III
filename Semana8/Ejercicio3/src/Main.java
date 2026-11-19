import controlador.PersonasControlador;
import modelo.PersonasModelo;
import vista.PersonasVista;

public class Main {
    public static void main(String[] args) {
        PersonasModelo modelo = new PersonasModelo();
        PersonasVista vista = new PersonasVista();
        PersonasControlador controlador = new PersonasControlador(modelo, vista);

        vista.establecerControlador(controlador);
        controlador.iniciar();
    }
}