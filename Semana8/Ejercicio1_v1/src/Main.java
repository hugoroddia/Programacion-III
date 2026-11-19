import controlador.QuinielaControlador;
import modelo.QuinielaModelo;
import vista.QuinielaVista;

public class Main {
    public static void main(String[] args) {
        QuinielaModelo modelo = new QuinielaModelo();
        QuinielaVista vista = new QuinielaVista();
        QuinielaControlador controlador = new QuinielaControlador(modelo, vista);

        vista.establecerControlador(controlador);
        controlador.iniciar();
    }
}
